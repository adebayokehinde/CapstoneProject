package com.example.truckstorm.exceptions;

public class invalidLoadException extends RuntimeException {
    public invalidLoadException(String message) {
        super(message);
    }
}
