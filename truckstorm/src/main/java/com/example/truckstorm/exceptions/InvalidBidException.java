package com.example.truckstorm.exceptions;

public class InvalidBidException extends RuntimeException {
    public InvalidBidException(String message) {
        super(message);
    }
}
