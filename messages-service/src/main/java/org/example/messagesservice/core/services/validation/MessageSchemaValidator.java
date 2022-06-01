package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;

public class MessageSchemaValidator implements IMessageValidator {

    @Override
    public boolean validate(Message message) {
        var content = message.getContent();
        return content.startsWith("http://") || content.startsWith("https://");
    }
}
