package com.example.truckstorm.exceptions;

public class DuplicateDriverException extends RuntimeException {
    public DuplicateDriverException(String message) {
        super(message);
    }
}
