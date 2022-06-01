package org.example.messagesservice.api.controller;

import org.example.messagesservice.api.exception.ApiBadRequestException;
import org.example.messagesservice.api.exception.ApiException;
import org.example.messagesservice.api.exception.ApiInternalException;
import org.example.messagesservice.api.exception.ApiNonExistentItemException;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.services.exception.ServiceNonExistentItemException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;

public class ControllerExceptionHandler extends ExceptionHandler<ApiException> {

    @Override
    protected ApiException createFrom(Exception e) {
        if (e instanceof ServiceValidationException) {
            return new ApiBadRequestException(e.getMessage());
        } else if (e instanceof ServiceNonExistentItemException) {
            return new ApiNonExistentItemException(e.getMessage());
        } else {
            return new ApiInternalException();
        }
    }
}
