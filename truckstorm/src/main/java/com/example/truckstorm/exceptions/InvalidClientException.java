package com.example.truckstorm.exceptions;

public class InvalidClientException extends RuntimeException {
    public InvalidClientException(String clientDoesNotExist) {
        super(clientDoesNotExist);
    }
}
