package com.deadlineforce.backend.security;

import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@ToString
public class CredentialsImpl implements Credentials {
    private final CharSequence raw;
    private final UserDetails userDetails;

    public CredentialsImpl(CharSequence raw, UserDetails userDetails) {
        this.raw = raw;
        this.userDetails = userDetails;
    }

    @Override
    public CharSequence getRawAuthentication() {
        return this.raw;
    }

    @Override
    public UserDetails getUserDetails() {
        return this.userDetails;
    }
}
