package org.example.messagesservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "We couldn't process your request, please contact customer support")
public class ApiInternalException extends ApiException {
}
