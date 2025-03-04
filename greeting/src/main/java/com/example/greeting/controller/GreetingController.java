package com.example.greeting.controller;

import com.example.greeting.model.Greeting;
import com.example.greeting.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping
    public Greeting getGreeting() {
        return greetingService.getGreeting();
    }

    //UC2
    // New endpoint to return the simple "Hello World" message
    @GetMapping("/simple")
    public Greeting getSimpleGreeting() {
        return greetingService.getSimpleGreeting(); // Calls the service layer for simple greeting
    }

    // GET /greeting/{firstName}/{lastName} - Returns greeting with first and last name
    @GetMapping("/{firstName}/{lastName}")
    public Greeting getGreetingWithNames(@PathVariable String firstName, @PathVariable String lastName) {
        Greeting greeting = greetingService.getGreetingWithName(firstName, lastName);
        if (greeting != null) {
            return greeting;
        }
        return greetingService.getGreeting(); // Fallback if one or both are missing
    }

    // GET /greeting/{name} - Returns greeting with single name (either first or last name)
    @GetMapping("/{name}")
    public Greeting getGreetingWithSingleName(@PathVariable String name) {
        Greeting greeting = greetingService.getGreetingWithSingleName(name);
        if (greeting != null) {
            return greeting;
        }
        return greetingService.getGreeting(); // Fallback to "Hello, world!" if no name is provided
    }

    @PostMapping
    public Greeting postGreeting(@RequestBody String name) {
        return greetingService.postGreeting(name);
    }

    @PutMapping
    public Greeting putGreeting(@RequestBody String name) {
        return greetingService.putGreeting(name);
    }

    @DeleteMapping
    public Greeting deleteGreeting(@RequestBody String name) {
        return greetingService.deleteGreeting(name);
    }
}
