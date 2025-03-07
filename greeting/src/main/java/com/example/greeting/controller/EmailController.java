package com.example.greeting.controller;

import com.example.greeting.dto.EmailRequest;
import com.example.greeting.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Endpoint to send a simple email
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
        return ResponseEntity.ok("Email sent successfully!");
    }

    // Endpoint to send an email with an attachment
    @PostMapping("/sendWithAttachment")
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam String attachmentPath) {
        emailService.sendEmailWithAttachment(to, subject, message, attachmentPath);
        return ResponseEntity.ok("Email with attachment sent successfully to " + to);
    }
}
