package com.example.truckstorm.exceptions;

public class InvalidDriverException extends RuntimeException {
    public InvalidDriverException(String message) {
        super(message);
    }
}
