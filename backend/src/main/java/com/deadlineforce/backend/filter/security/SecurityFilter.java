package com.deadlineforce.backend.filter.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.deadlineforce.backend.security.AuthenticationException;
import com.deadlineforce.backend.security.AuthenticationProvider;
import com.deadlineforce.backend.security.CredentialsImpl;
import com.deadlineforce.backend.security.jwt.JWTUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityFilter implements Filter {
    private final AuthenticationProvider authenticationProvider;
    private final JWTUtils JWTUtils;

    public SecurityFilter(AuthenticationProvider authenticationProvider, JWTUtils JWTUtils) {
        this.authenticationProvider = authenticationProvider;
        this.JWTUtils = JWTUtils;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String authenticationHeader = ((HttpServletRequest) servletRequest).getHeader(HttpHeaders.AUTHORIZATION);

            if (authenticationHeader != null && authenticationHeader.matches("Bearer .*")) {
                UserDetails userDetails = JWTUtils.getUserDetails(authenticationHeader.split(" ")[1]);
                Authentication authentication = this.authenticationProvider
                        .authenticate(new CredentialsImpl(authenticationHeader, userDetails));

                if (authentication == null) {
                    throw new RuntimeException("auth is null");
                }

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthenticationException | JWTVerificationException e) {
            ((HttpServletResponse) servletResponse).setStatus(HttpStatus.FORBIDDEN.value());
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
