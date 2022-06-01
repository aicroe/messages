package org.example.messagesservice.persistence.exception;

public class DaoDuplicatedKeyException extends DaoException {

    public DaoDuplicatedKeyException(String message) {
        super(message);
    }
}
