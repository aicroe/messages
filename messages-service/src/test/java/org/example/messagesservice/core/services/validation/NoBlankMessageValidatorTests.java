package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NoBlankMessageValidatorTests {
    @Test
    public void validate_blankMessage_returnsFalse() {
        IMessageValidator validator = new NoBlankMessageValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("    ");

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }

    @Test
    public void validate_nonBlankMessage_returnsTrue() {
        IMessageValidator validator = new NoBlankMessageValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("   hey   ");

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }
}
