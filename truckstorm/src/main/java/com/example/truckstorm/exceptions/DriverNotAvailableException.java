package com.example.truckstorm.exceptions;

public class DriverNotAvailableException extends RuntimeException {
    public DriverNotAvailableException(String message) {
        super(message);
    }
}
