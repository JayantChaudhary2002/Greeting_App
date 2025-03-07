package com.example.greeting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_EMAIL = "your-email@gmail.com"; // Set your email here

    // Method to send a simple email
    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(FROM_EMAIL);

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    // Method to send an email with an attachment
    public void sendEmailWithAttachment(String to, String subject, String text, String attachmentPath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(FROM_EMAIL);

            // Add attachment
            File file = new File(attachmentPath);
            if (file.exists()) {
                helper.addAttachment(file.getName(), file);
            } else {
                System.err.println("Attachment not found: " + attachmentPath);
            }

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email with attachment: " + e.getMessage());
        }
    }

    // Method to send an HTML email
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true for HTML content
            helper.setFrom(FROM_EMAIL);

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending HTML email: " + e.getMessage());
        }
    }

    // Method to send an email with an inline image
    public void sendEmailWithInlineImage(String to, String subject, String htmlContent, String imagePath, String imageId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true for HTML
            helper.setFrom(FROM_EMAIL);

            // Add inline image
            File file = new File(imagePath);
            if (file.exists()) {
                helper.addInline(imageId, file);
            } else {
                System.err.println("Inline image not found: " + imagePath);
            }

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email with inline image: " + e.getMessage());
        }
    }

    // Method to send a password reset confirmation email
    public void sendPasswordResetConfirmation(String to) {
        String subject = "Password Change Confirmation";
        String text = "Your password has been successfully updated.";
        sendEmail(to, subject, text);
    }
}
