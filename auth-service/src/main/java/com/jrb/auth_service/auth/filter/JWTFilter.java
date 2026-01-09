package com.jrb.auth_service.auth.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.jrb.auth_service.utils.jwt.JWTUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_TYPE = "Bearer ";

    private JWTUtils jwt;
    private UserDetailsService userDetailsService;
    private HandlerExceptionResolver resolver;

    public JWTFilter(JWTUtils jwt, UserDetailsService userDetailsService,
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader(AUTHENTICATION_HEADER);
            String stringToken = null;
            String email = null;
            SecurityContext context = SecurityContextHolder.getContext();
            // Verify the jwt token
            if (authHeader != null && authHeader.startsWith(AUTHENTICATION_TYPE)) {
                stringToken = authHeader.substring(7);
                email = jwt.verifyToken(stringToken).email();
            }
            // Verify if the request already authenticated
            if (email != null && context.getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null,
                        userDetails.getAuthorities());

                context.setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            logger.debug(ex.getStackTrace());
            resolver.resolveException(request, response, null, ex);
        }
    }

}
