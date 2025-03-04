package com.example.greeting.model;

public class Greeting {
    private String message;

    public Greeting(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}