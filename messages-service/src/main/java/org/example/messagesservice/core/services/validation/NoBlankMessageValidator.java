package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;

public class NoBlankMessageValidator implements IMessageValidator {

    @Override
    public boolean validate(Message message) {
        return !message.getContent().trim().isEmpty();
    }
}
