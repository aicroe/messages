package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;

import java.util.List;

public class ComposableMessageValidator implements IMessageValidator {

    private List<IMessageValidator> validators;

    public ComposableMessageValidator(List<IMessageValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(Message message) {
        return validators.stream().allMatch(validator -> validator.validate(message));
    }
}
