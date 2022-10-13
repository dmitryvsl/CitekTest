package com.example.citektest.domain.exceptions;

public class RequestErrorException extends RuntimeException{

    public RequestErrorException() {
    }

    public RequestErrorException(String message) {
        super(message);
    }
}
