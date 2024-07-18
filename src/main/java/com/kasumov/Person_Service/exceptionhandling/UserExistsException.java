package com.kasumov.Person_Service.exceptionhandling;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
