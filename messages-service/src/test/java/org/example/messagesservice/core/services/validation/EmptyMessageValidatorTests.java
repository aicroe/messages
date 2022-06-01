package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class EmptyMessageValidatorTests {
    @Test
    public void validate_emptyMessage_returnsFalse() {
        IMessageValidator validator = new EmptyMessageValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.isEmpty()).thenReturn(true);

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }

    @Test
    public void validate_nonEmptyMessage_returnsTrue() {
        IMessageValidator validator = new EmptyMessageValidator();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.isEmpty()).thenReturn(false);

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }
}
