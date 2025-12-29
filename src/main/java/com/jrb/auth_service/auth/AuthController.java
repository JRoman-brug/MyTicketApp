package com.jrb.auth_service.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.auth_service.user.entity.UserEntity;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log
@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable Long userId) {
        return authService.getUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> postMethodName() {
        return new ResponseEntity<>("Hello world", HttpStatus.ACCEPTED);
    }

}
