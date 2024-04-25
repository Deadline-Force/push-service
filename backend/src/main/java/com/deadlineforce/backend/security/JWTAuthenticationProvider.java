package com.deadlineforce.backend.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Credentials credentials) {
        Objects.requireNonNull(credentials);

        if (credentials.getUserDetails().isCredentialsNonExpired()) {


            return new UsernamePasswordAuthenticationToken(credentials.getUserDetails(),
                    credentials.getUserDetails().getPassword(), credentials.getUserDetails().getAuthorities());


        } else {
            throw new AuthenticationException(credentials.toString());
        }
    }
}
