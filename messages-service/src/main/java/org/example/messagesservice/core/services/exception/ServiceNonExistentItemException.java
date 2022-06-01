package org.example.messagesservice.core.services.exception;

public class ServiceNonExistentItemException extends ServiceException {

    public ServiceNonExistentItemException(String message) {
        super(message);
    }
}
