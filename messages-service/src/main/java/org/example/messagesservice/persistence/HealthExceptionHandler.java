package org.example.messagesservice.persistence;

import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.persistence.exception.DaoException;
import org.example.messagesservice.persistence.exception.DaoInternalException;

public class HealthExceptionHandler extends ExceptionHandler<DaoException> {

    @Override
    protected DaoException createFrom(Exception e) {
        return new DaoInternalException(e.getMessage());
    }
}
