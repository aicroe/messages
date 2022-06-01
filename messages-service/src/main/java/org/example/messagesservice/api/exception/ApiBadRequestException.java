package org.example.messagesservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiBadRequestException extends ApiException {

    public ApiBadRequestException(String message) {
        super(message);
    }
}
