package com.example.greeting.controller;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.exception.WeakJwtKeyException;
import com.example.greeting.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Tag(name = "Authentication", description = "API for user authentication")
@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        try {
            String response = authenticationService.registerUser(authUserDTO);
            return ResponseEntity.ok(response);
        } catch (WeakJwtKeyException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Login user", description = "Authenticates a user and returns a JWT token")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            String token = authenticationService.loginUser(loginDTO);
            return ResponseEntity.ok(token);
        } catch (WeakJwtKeyException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(WeakJwtKeyException.class)
    public ResponseEntity<String> handleWeakJwtKeyException(WeakJwtKeyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email, @RequestBody String newPassword) {
        String response = authenticationService.forgotPassword(email, newPassword);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<String> resetPassword(
            @PathVariable String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {
        String response = authenticationService.resetPassword(email, currentPassword, newPassword);
        return ResponseEntity.ok(response);
    }
}
