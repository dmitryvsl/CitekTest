package com.example.citektest.domain.exceptions;

public class UnknownException extends RuntimeException{

    public UnknownException() {
    }

    public UnknownException(String message) {
        super(message);
    }
}
