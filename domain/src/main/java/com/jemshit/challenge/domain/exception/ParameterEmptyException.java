package com.jemshit.challenge.domain.exception;

public class ParameterEmptyException extends Exception {

    public ParameterEmptyException() {
    }

    public ParameterEmptyException(String message) {
        super(message);
    }
}
