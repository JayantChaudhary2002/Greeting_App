package com.example.greeting.controller;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.exception.WeakJwtKeyException;
import com.example.greeting.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    // Register user endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        try {
            String response = authenticationService.registerUser(authUserDTO);
            return ResponseEntity.ok(response);
        } catch (WeakJwtKeyException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Login user endpoint
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            String token = authenticationService.loginUser(loginDTO);
            return ResponseEntity.ok(token);
        } catch (WeakJwtKeyException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Global exception handler for WeakJwtKeyException
    @ExceptionHandler(WeakJwtKeyException.class)
    public ResponseEntity<String> handleWeakJwtKeyException(WeakJwtKeyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}