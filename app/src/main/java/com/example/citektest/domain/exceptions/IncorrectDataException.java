package com.example.citektest.domain.exceptions;

public class IncorrectDataException extends RuntimeException{

    public IncorrectDataException() {
    }

    public IncorrectDataException(String message) {
        super(message);
    }
}
