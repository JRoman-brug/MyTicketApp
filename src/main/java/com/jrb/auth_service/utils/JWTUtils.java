package com.jrb.auth_service.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTUtils {

    static Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    @Value("${jwt.expiration}")
    private long expirationTime;
    @Value("${jwt.secret}")
    private String secret;
    private SecretKey key;

    @PostConstruct
    public void init() {
        logger.debug("Loading secrect");
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generatedToke(String email) {
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public void verifyToken(String token) {
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getSubjec(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String getId(String token) {
        return extractClaims(token, Claims::getId);
    }

    public Date getIssuiedAt(String token) {
        return extractClaims(token, Claims::getIssuedAt);
    }

    public Date getExperitionTime(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
