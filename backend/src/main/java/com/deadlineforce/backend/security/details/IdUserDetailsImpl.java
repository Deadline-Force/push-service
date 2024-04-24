package com.deadlineforce.backend.security.details;

import com.deadlineforce.backend.security.IdUserDetails;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

@ToString
public class IdUserDetailsImpl implements IdUserDetails {
    private final long userId;
    private final String username, password;
    private final Collection<GrantedAuthority> authorities = new HashSet<>();
    @Setter
    private boolean expired = false;

    public IdUserDetailsImpl(long userId, String username, String password) {
        this.userId = userId;
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

    @Override
    public long getId() {
        return this.userId;
    }
}
