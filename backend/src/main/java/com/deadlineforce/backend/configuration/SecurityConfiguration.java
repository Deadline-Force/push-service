package com.deadlineforce.backend.configuration;

import com.deadlineforce.backend.security.details.AuthorizationRoles;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final Filter securityFilter;

    public SecurityConfiguration(Filter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/users/**").hasRole(AuthorizationRoles.ADMIN.name())
                .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole(AuthorizationRoles.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                .requestMatchers("/auth/logout").authenticated()
                .requestMatchers("/auth/login", "/auth/signup").permitAll()
                .requestMatchers("/notifications/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .build();
    }
}
