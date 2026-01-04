package com.jrb.auth_service.utils.jwt;

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

    public TokenInfo verifyToken(String token) {
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        String jti = getId(payload);
        String email = getSubjec(payload);
        Date issueAt = getIssuiedAt(payload);
        Date expiritaionTime = getExperitionTime(payload);
        return new TokenInfo(jti, email, issueAt, expiritaionTime);
    }

    private <T> T extractClaims(Claims payload, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(payload);
    }

    private String getSubjec(Claims payload) {
        return extractClaims(payload, Claims::getSubject);
    }

    private String getId(Claims payload) {
        return extractClaims(payload, Claims::getId);
    }

    private Date getIssuiedAt(Claims payload) {
        return extractClaims(payload, Claims::getIssuedAt);
    }

    private Date getExperitionTime(Claims payload) {
        return extractClaims(payload, Claims::getExpiration);
    }
}
