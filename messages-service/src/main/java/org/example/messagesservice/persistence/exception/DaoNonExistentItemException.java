package org.example.messagesservice.persistence.exception;

public class DaoNonExistentItemException extends DaoInternalException {

    public DaoNonExistentItemException(String message) {
        super(message);
    }
}
