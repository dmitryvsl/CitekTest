package com.example.citektest.domain.exceptions;

public class NullBodyResponseException extends RuntimeException{

    public NullBodyResponseException() {
    }

    public NullBodyResponseException(String message) {
        super(message);
    }
}
