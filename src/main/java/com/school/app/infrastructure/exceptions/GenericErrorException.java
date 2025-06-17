package com.school.app.infrastructure.exceptions;

public class GenericErrorException extends RuntimeException {
    public GenericErrorException(String message) {
        super(message);
    }
}
