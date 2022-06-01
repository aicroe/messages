package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;

public class MaximumLengthValidator implements IMessageValidator {

    private int maximumLength;
    private boolean acceptNull;

    public MaximumLengthValidator(int maximumLength, boolean acceptNull) {
        this.maximumLength = maximumLength;
        this.acceptNull = acceptNull;
    }

    @Override
    public boolean validate(Message message) {
        if (acceptNull && message.getContent() == null) {
            return true;
        }
        var content = message.getContent();
        return content != null && content.length() <= maximumLength;
    }
}
