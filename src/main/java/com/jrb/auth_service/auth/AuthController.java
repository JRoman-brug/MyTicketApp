package com.jrb.auth_service.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.auth_service.user.entity.User;

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
    public User getUser(@PathVariable Long userId) {
        return authService.getUser(userId);
    }
}
