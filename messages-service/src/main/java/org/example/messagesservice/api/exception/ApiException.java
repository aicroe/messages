package org.example.messagesservice.api.exception;

public class ApiException extends Exception {

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}
