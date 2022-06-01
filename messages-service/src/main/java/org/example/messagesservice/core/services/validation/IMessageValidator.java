package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.domain.MessageType;

import java.util.List;

public interface IMessageValidator {

    boolean validate(Message message);

    static IMessageValidator getValidator(MessageType type) {
        if (type == MessageType.Text) {
            return new ComposableMessageValidator(List.of(
                new EmptyMessageValidator(),
                new NoBlankMessageValidator()
            ));
        } else if (type == MessageType.Image) {
            return new ComposableMessageValidator(List.of(
                new EmptyMessageValidator(),
                new NoMiddleBlankValidator(),
                new MessageSchemaValidator()
            ));
        } else if (type == MessageType.Short) {
            return new MaximumLengthValidator(280, true);
        } else {
            throw new IllegalArgumentException("Unknown message type");
        }
    }
}
