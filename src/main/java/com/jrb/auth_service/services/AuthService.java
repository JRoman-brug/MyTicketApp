package com.jrb.auth_service.services;

import org.springframework.stereotype.Service;

import com.jrb.auth_service.entity.User;
import com.jrb.auth_service.exception.UserNotFoundException;
import com.jrb.auth_service.repository.UserRepository;

@Service
public class AuthService {
    private UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(String userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id: " + userId));
    }
}
