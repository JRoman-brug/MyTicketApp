package com.jrb.auth_service.auth;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jrb.auth_service.auth.dto.LoginRequestDTO;
import com.jrb.auth_service.auth.dto.LoginResponseDTO;
import com.jrb.auth_service.auth.dto.LogoutRequestDTO;
import com.jrb.auth_service.auth.dto.LogoutResponseDTO;
import com.jrb.auth_service.auth.dto.RegisterRequestDTO;
import com.jrb.auth_service.auth.dto.RegisterResponseDTO;
import com.jrb.auth_service.auth.entity.RevokeTokenEntity;
import com.jrb.auth_service.auth.exceptions.EmailAlreadyUserException;
import com.jrb.auth_service.auth.mappers.UserMapper;
import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.UserEntity;
import com.jrb.auth_service.user.exception.UserNotFoundException;
import com.jrb.auth_service.utils.JWTUtils;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RevokedTokenRepository tokenRepository;
    private UserMapper mapper;
    private AuthenticationManager authorizationManager;
    private PasswordEncoder encoder;
    private JWTUtils jwt;

    public AuthService(UserRepository userRepository, AuthenticationManager authorizationManager, UserMapper mapper,
            PasswordEncoder encoder, JWTUtils jwt, RevokedTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authorizationManager = authorizationManager;
        this.encoder = encoder;
        this.jwt = jwt;
        this.tokenRepository = tokenRepository;
    }

    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario con id: " + userId));
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.email(),
                request.password());
        authorizationManager.authenticate(authenticationRequest);
        UserEntity user = userRepository.findByEmail(request.email());
        String token = jwt.generatedToke(user.getEmail());
        return new LoginResponseDTO(user.getEmail(), token, "Login successfully");
    }

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        UserEntity user = mapper.registerDtoToUser(request);
        boolean existUser = userRepository.existsByEmail(user.getEmail());
        if (existUser)
            throw new EmailAlreadyUserException("Email is used");
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        String token = jwt.generatedToke(user.getEmail());
        return new RegisterResponseDTO(user.getEmail(), token, "null");
    }

    public LogoutResponseDTO logout(LogoutRequestDTO request) {
        String token = request.token();
        jwt.verifyToken(token);
        String jti = jwt.getId(token);
        String email = jwt.getSubjec(token);
        Date expiritionTime = jwt.getExperitionTime(token);
        RevokeTokenEntity revokedToken = new RevokeTokenEntity(jti, email, new Date(), expiritionTime);
        tokenRepository.save(revokedToken);
        return new LogoutResponseDTO("Logout sucessfully");
    }
}
