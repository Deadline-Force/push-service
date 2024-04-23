package com.deadlineforce.backend.security.details;

import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@ToString
public class UserDetailsImpl implements UserDetails {
    private final String username, password;
    private final Collection<GrantedAuthority> authorities = new HashSet<>();
    @Setter
    private boolean expired = false;

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setAuthority(AuthorizationRoles role) {
        for (AuthorizationRoles authRole : AuthorizationRoles.asList(role)) {
            this.authorities.add(new SimpleGrantedAuthority(authRole.name()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        throw new RuntimeException();
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new RuntimeException();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.expired;
    }

    @Override
    public boolean isEnabled() {
        throw new RuntimeException();
    }
}
