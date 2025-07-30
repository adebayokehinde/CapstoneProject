package com.example.truckstorm.exceptions;

public class DuplicateClientException extends RuntimeException {
  public DuplicateClientException(String message) {
    super(message);
  }
}
