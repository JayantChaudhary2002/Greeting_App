package com.example.greeting.controller;

import com.example.greeting.model.Greeting;
import com.example.greeting.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    // Save a new greeting
    @PostMapping
    public Greeting createGreeting(@RequestBody String message) {
        return greetingService.saveGreeting(message);
    }

    // New endpoint to list all greetings
    @GetMapping
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    // Get a greeting by ID
    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    // New endpoint to edit a greeting message
    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody String newMessage) {
        return greetingService.updateGreeting(id, newMessage);
    }

    // New endpoint to delete a greeting message
    @DeleteMapping("/{id}")
    public String deleteGreeting(@PathVariable Long id) {
        boolean isDeleted = greetingService.deleteGreeting(id);
        if (isDeleted) {
            return "Greeting deleted successfully!";
        } else {
            return "Greeting not found!";
        }
    }
}
