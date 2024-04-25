package com.deadlineforce.backend.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse http = (HttpServletResponse) servletResponse;
        http.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        http.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        http.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}