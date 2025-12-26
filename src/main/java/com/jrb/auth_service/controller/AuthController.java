package com.jrb.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.auth_service.entity.User;
import com.jrb.auth_service.services.AuthService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return authService.getUser(userId);
    }
}
