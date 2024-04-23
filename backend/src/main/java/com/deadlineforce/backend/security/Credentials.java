package com.deadlineforce.backend.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface Credentials {
    CharSequence getRawAuthentication();
    UserDetails getUserDetails();
}
