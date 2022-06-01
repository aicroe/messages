package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MessageSchemaValidatorTests {
    @Test
    public void validate_messageWithoutSchema_returnsFalse() {
        IMessageValidator validator = new MessageSchemaValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("any content");

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }

    @Test
    public void validate_messageWithHttpSchema_returnsTrue() {
        IMessageValidator validator = new MessageSchemaValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("http://example.org");

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }

    @Test
    public void validate_messageWithHttpsSchema_returnsTrue() {
        IMessageValidator validator = new MessageSchemaValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("https://example.org");

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }
}
