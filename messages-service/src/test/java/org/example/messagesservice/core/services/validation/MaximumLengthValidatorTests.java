package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class MaximumLengthValidatorTests {

    @Test
    public void validate_emptyMessageContent_returnsTrue() {
        IMessageValidator validator = new MaximumLengthValidator(100, false);
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("");

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }

    @Test
    public void validate_nullContent_returnsFalse() {
        IMessageValidator validator = new MaximumLengthValidator(100, false);
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn(null);

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }

    @Test
    public void validate_nullContentAndAcceptsNull_returnsTrue() {
        IMessageValidator validator = new MaximumLengthValidator(100, true);
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn(null);

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }

    @Test
    public void validate_shortMessage_returnsTrue() {
        IMessageValidator validator = new MaximumLengthValidator(10, false);
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("hello");

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }

    @Test
    public void validate_largeMessage_returnsFalse() {
        IMessageValidator validator = new MaximumLengthValidator(5, false);
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getContent()).thenReturn("Hello World!");

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }
}
