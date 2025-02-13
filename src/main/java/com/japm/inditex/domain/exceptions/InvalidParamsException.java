package com.japm.inditex.domain.exceptions;

public class InvalidParamsException extends RuntimeException {
    public InvalidParamsException() {
        super("Invalid parameters provided");
    }

    public InvalidParamsException(String message) {
        super(message);
    }
}
