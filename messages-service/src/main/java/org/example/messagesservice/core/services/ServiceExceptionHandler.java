package org.example.messagesservice.core.services;

import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceInternalException;
import org.example.messagesservice.core.services.exception.ServiceNonExistentItemException;
import org.example.messagesservice.persistence.exception.DaoException;
import org.example.messagesservice.persistence.exception.DaoNonExistentItemException;

public class ServiceExceptionHandler extends ExceptionHandler<ServiceException> {

    @Override
    protected ServiceException createFrom(Exception e) {
        if (e instanceof DaoNonExistentItemException) {
            return new ServiceNonExistentItemException(e.getMessage());
        } else if (e instanceof DaoException) {
            return new ServiceInternalException(e.getMessage());
        } else {
            return new ServiceInternalException(e.getMessage());
        }
    }
}
