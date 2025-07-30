package com.example.truckstorm.exceptions;

public class InvalidLoadStatusException extends RuntimeException {
    public InvalidLoadStatusException(String message) {
        super(message);
    }
}
