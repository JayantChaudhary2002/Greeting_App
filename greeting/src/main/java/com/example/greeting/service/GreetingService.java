package com.example.greeting.service;

import com.example.greeting.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

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
