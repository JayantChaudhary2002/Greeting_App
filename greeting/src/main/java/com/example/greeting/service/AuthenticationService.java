
package com.example.greeting.service;

import com.example.greeting.dto.AuthUserDTO;
import com.example.greeting.dto.LoginDTO;
import com.example.greeting.model.AuthUser;
import com.example.greeting.repository.AuthUserRepository;
import com.example.greeting.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService; // Inject EmailService

    public String registerUser(AuthUserDTO authUserDTO) {
        Optional<AuthUser> existingUser = authUserRepository.findByEmail(authUserDTO.getEmail());
        if (existingUser.isPresent()) {
            return "Email is already registered!";
        }

        // Create new user
        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        // Save user to database
        authUserRepository.save(user);

        // Generate JWT token for the newly registered user
        String token = jwtUtil.generateToken(authUserDTO.getEmail());

        // Send JWT token to user's email
        String subject = "Welcome! Your Registration Token";
        String emailContent = "Hello " + authUserDTO.getFirstName() + ",\n\n" +
                "Thank you for registering!\n\n" +
                "Here is your JWT token:\n\n" + token + "\n\n" +
                "Use this token to access our services securely.\n\n" +
                "Best Regards,\nYour Company Name";

        emailService.sendEmail(authUserDTO.getEmail(), subject, emailContent);

        return "User registered successfully! JWT token sent to email.";
    }
    public String loginUser(LoginDTO loginDTO) {  // Ensure method signature matches
        Optional<AuthUser> user = authUserRepository.findByEmail(loginDTO.getEmail());

        if (user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            return jwtUtil.generateToken(loginDTO.getEmail());
        }
        return "Invalid email or password!";
    }
    public String forgotPassword(String email, String newPassword) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "Sorry! We cannot find the user email: " + email;
        }

        AuthUser user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);

        emailService.sendPasswordResetConfirmation(email);
        return "Password has been changed successfully!";
    }

    public String resetPassword(String email, String currentPassword, String newPassword) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "User not found with email: " + email;
        }

        AuthUser user = userOptional.get();
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return "Current password is incorrect!";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        authUserRepository.save(user);
        return "Password reset successfully!";
    }
}
