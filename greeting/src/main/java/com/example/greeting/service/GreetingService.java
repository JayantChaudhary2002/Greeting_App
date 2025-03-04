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

    // New method to update a greeting message
    public Greeting updateGreeting(Long id, String newMessage) {
        Optional<Greeting> optionalGreeting = greetingRepository.findById(id);
        if (optionalGreeting.isPresent()) {
            Greeting greeting = optionalGreeting.get();
            greeting.setMessage(newMessage);
            return greetingRepository.save(greeting);  // Save updated greeting
        }
        return null;  // Return null if not found
    }

    // New method to delete a greeting message by ID
    public boolean deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)) {
            greetingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
