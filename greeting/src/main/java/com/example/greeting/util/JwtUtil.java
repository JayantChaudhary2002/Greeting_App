package com.example.greeting.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import com.example.greeting.exception.WeakJwtKeyException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Generate a secure key for HS256 algorithm using the io.jsonwebtoken.security.Keys class
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Method to generate a JWT token
    public String generateToken(String email) {
        // Check if the key's size is smaller than the minimum required size (256 bits for HS256)
        if (SECRET_KEY.getEncoded().length * 8 < 256) {
            throw new WeakJwtKeyException("The signing key's size is too small for HS256 algorithm.");
        }

        // Generate the JWT token with subject, issue time, and expiration time
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(SECRET_KEY)
                .compact();
    }
}
