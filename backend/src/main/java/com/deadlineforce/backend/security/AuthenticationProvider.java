package com.deadlineforce.backend.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationProvider {
    Authentication authenticate(Credentials credentials);
}
