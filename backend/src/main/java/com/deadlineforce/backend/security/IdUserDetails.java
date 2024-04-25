package com.deadlineforce.backend.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface IdUserDetails extends UserDetails {
    long getId();
}
