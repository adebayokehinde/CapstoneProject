package com.example.truckstorm.exceptions;

public class LoadNotFoundException extends RuntimeException {
    public LoadNotFoundException(String message) {
        super(message);
    }
}
