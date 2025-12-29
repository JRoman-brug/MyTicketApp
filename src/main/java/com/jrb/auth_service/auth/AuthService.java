package com.jrb.auth_service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jrb.auth_service.auth.dto.LoginRequestDTO;
import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.UserEntity;
import com.jrb.auth_service.user.exception.UserNotFoundException;

@Service
public class AuthService {
    private UserRepository repository;
    private AuthenticationManager authorizationManager;

    public AuthService(UserRepository repository, AuthenticationManager authorizationManager) {
        this.repository = repository;
        this.authorizationManager = authorizationManager;
    }

    public UserEntity getUser(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id: " + userId));
    }

    public void login(LoginRequestDTO request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.email(),
                request.password());
        authorizationManager.authenticate(authenticationRequest);
    }
}
