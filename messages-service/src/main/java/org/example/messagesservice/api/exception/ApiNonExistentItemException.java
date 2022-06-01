package org.example.messagesservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiNonExistentItemException extends ApiException {

    public ApiNonExistentItemException(String message) {
        super(message);
    }
}
