package com.example.truckstorm.exceptions;

public class InvalidLoadException extends RuntimeException {
    public InvalidLoadException(String message) {
        super(message);
    }
}
