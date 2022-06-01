package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NoMiddleBlankValidatorTests {
    @Test
    public void validate_messageWithMiddleBlank_returnsFalse() {
        IMessageValidator validator = new NoMiddleBlankValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("Hello World!");

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }

    @Test
    public void validate_messageWithoutBlanks_returnsTrue() {
        IMessageValidator validator = new NoMiddleBlankValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("https://example.org/image.jpg");

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }
}
