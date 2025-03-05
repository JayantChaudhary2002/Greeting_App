package com.example.greeting.exception;

// Custom exception class for weak JWT key
public class WeakJwtKeyException extends RuntimeException {

    // Default constructor
    public WeakJwtKeyException() {
        super("The signing key's size is too small for HS256 algorithm. A key of at least 256 bits is required.");
    }

    // Constructor with custom message
    public WeakJwtKeyException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public WeakJwtKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public WeakJwtKeyException(Throwable cause) {
        super(cause);
    }
}
