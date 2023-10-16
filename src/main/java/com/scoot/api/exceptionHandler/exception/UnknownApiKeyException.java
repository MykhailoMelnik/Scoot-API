package com.scoot.api.exceptionHandler.exception;

public class UnknownApiKeyException extends RuntimeException {
    public UnknownApiKeyException(String message) {
        super(message);
    }
}
