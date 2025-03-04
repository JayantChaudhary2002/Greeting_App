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

    // UC3
    // Method to return greeting with both First and Last name
    public Greeting getGreetingWithName(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return new Greeting("Hello, " + firstName + " " + lastName + "!");
        }
        return null; // We'll handle other cases in the controller
    }

    // Method to return greeting with just First Name or Last Name
    public Greeting getGreetingWithSingleName(String name) {
        if (name != null) {
            return new Greeting("Hello, " + name + "!");
        }
        return null;
    }

    // Method to return the default "Hello, world!"
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
