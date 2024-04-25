package com.deadlineforce.backend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.deadlineforce.backend.security.details.AuthorizationRoles;
import com.deadlineforce.backend.security.details.IdUserDetailsImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@PropertySource("/security.properties")
public class JWTUtils {
    public static final String USER_ID_KEY = "userId";
    public static final String USERNAME_KEY = "username";
    public static final String ROLE_KEY = "role";

    @Value("${jwt.secret}")
    private String JWTSecret;
    private Algorithm algorithm;
    private JWTVerifier JWTVerifier;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC512(this.JWTSecret);
        this.JWTVerifier = JWT.require(this.algorithm).build();

        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String createJWT(long userId, String username, String role, long expires, TimeUnit unit) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + unit.toMillis(expires)))
                .withClaim(USER_ID_KEY, userId)
                .withClaim(USERNAME_KEY, username)
                .withClaim(ROLE_KEY, role)
                .sign(this.algorithm);
    }

    public UserDetails getUserDetails(String JWTToken) {
        try {
            DecodedJWT decodedJWT = this.JWTVerifier.verify(JWTToken);
            JWTPayload payload = this.objectMapper
                    .readValue(Base64.getDecoder().decode(decodedJWT.getPayload()), JWTPayload.class);
            IdUserDetailsImpl userDetails = new IdUserDetailsImpl(payload.userId(), payload.username(), "");
            userDetails.setAuthority(AuthorizationRoles.valueOf(payload.role()));
            return userDetails;
        } catch (IOException e) {
            throw new JWTVerificationException(e.getMessage(), e);
        }
    }

    record JWTPayload(long userId, String username, String role) {
    }
}
