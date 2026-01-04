package com.jrb.auth_service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrb.auth_service.auth.dto.LoginRequestDTO;
import com.jrb.auth_service.auth.dto.LoginResponseDTO;
import com.jrb.auth_service.auth.dto.RegisterRequestDTO;
import com.jrb.auth_service.auth.dto.RegisterResponseDTO;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Log
@RestController
@RequestMapping("auth")
public class AuthController {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    private AuthService authService;
    static Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(AUTHENTICATION_HEADER) String authToken) {
        authService.logout(authToken);
        return new ResponseEntity<>("Logout was successfully", HttpStatus.ACCEPTED);
    }

}
