package com.jrb.auth_service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.auth_service.auth.dto.LoginRequestDTO;
import com.jrb.auth_service.auth.dto.LoginResponseDTO;
import com.jrb.auth_service.auth.dto.RegisterRequestDTO;
import com.jrb.auth_service.auth.dto.RegisterResponseDTO;
import com.jrb.auth_service.user.entity.UserEntity;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log
@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;
    static Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable Long userId) {
        return authService.getUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return authService.register(request);
    }

}
