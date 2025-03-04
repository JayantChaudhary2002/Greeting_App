package com.example.greeting.service;

import com.example.greeting.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    // UC2
    // Method to return the simple "Hello World" greeting
    public Greeting getSimpleGreeting() {
        return new Greeting("Hello World");
    }

    public Greeting getGreeting() {
        return new Greeting("Hello, world!");
    }

    public Greeting postGreeting(String name) {
        return new Greeting("Hello, " + name + "!");
    }

    public Greeting putGreeting(String name) {
        return new Greeting("Updated greeting for, " + name + "!");
    }

    public Greeting deleteGreeting(String name) {
        return new Greeting("Deleted greeting for, " + name + "!");
    }
}
