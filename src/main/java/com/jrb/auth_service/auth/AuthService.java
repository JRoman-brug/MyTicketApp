package com.jrb.auth_service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jrb.auth_service.auth.dto.LoginRequestDTO;
import com.jrb.auth_service.auth.dto.LoginResponseDTO;
import com.jrb.auth_service.auth.dto.RegisterRequestDTO;
import com.jrb.auth_service.auth.dto.RegisterResponseDTO;
import com.jrb.auth_service.auth.exceptions.EmailAlreadyUserException;
import com.jrb.auth_service.auth.mappers.UserMapper;
import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.UserEntity;
import com.jrb.auth_service.user.exception.UserNotFoundException;
import com.jrb.auth_service.utils.JWTUtils;

@Service
public class AuthService {
    private UserRepository repository;
    private UserMapper mapper;
    private AuthenticationManager authorizationManager;
    private PasswordEncoder encoder;
    private JWTUtils jwt;

    public AuthService(UserRepository repository, AuthenticationManager authorizationManager, UserMapper mapper,
            PasswordEncoder encoder, JWTUtils jwt) {
        this.repository = repository;
        this.mapper = mapper;
        this.authorizationManager = authorizationManager;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public UserEntity getUser(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id: " + userId));
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.email(),
                request.password());
        authorizationManager.authenticate(authenticationRequest);
        UserEntity user = repository.findByEmail(request.email());
        String token = jwt.generatedToke(user.getEmail());
        return new LoginResponseDTO(user.getEmail(), token, "Login successfully");
    }

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        UserEntity user = mapper.registerDtoToUser(request);
        boolean existUser = repository.existsByEmail(user.getEmail());
        if (existUser)
            throw new EmailAlreadyUserException("Email is used");
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        repository.save(user);
        String token = jwt.generatedToke(user.getEmail());
        return new RegisterResponseDTO(user.getEmail(), token, "null");
    }
}
