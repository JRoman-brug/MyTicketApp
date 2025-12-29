package com.jrb.auth_service.auth;

import org.springframework.stereotype.Service;

import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.UserEntity;
import com.jrb.auth_service.user.exception.UserNotFoundException;

@Service
public class AuthService {
    private UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity getUser(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id: " + userId));
    }
}
