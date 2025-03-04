package com.example.greeting.service;

import com.example.greeting.model.Greeting;
import com.example.greeting.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    // Save a greeting message
    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }


    // Retrieve a single greeting by ID
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id).orElse(null);
    }

    // New method to list all greetings
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // Update a greeting message
    public Greeting updateGreeting(Long id, String newMessage) {
        Greeting greeting = greetingRepository.findById(id).orElse(null);
        if (greeting != null) {
            greeting.setMessage(newMessage);
            return greetingRepository.save(greeting);
        }
        return null;
    }

    // Delete a greeting by ID
    public void deleteGreeting(Long id) {
        greetingRepository.deleteById(id);
    }
}
