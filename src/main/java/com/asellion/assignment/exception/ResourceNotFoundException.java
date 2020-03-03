package com.asellion.assignment.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Resource not found with given id: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
