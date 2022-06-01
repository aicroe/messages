package org.example.messagesservice.core.services.validation;

import org.example.messagesservice.core.domain.Message;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class ComposableMessageValidatorTests {
    @Test
    public void validate_allValidatorsAccept_returnsTrue() {
        IMessageValidator embedded = Mockito.mock(IMessageValidator.class);
        Mockito.when(embedded.validate(Mockito.any(Message.class))).thenReturn(true);
        IMessageValidator validator = new ComposableMessageValidator(List.of(
            embedded,
            embedded,
            embedded
        ));
        Message message = Mockito.mock(Message.class);

        boolean valid = validator.validate(message);

        Assert.assertTrue(valid);
    }

    @Test
    public void validate_oneValidatorRejects_returnsFalse() {
        IMessageValidator embedded = Mockito.mock(IMessageValidator.class);
        Mockito.when(embedded.validate(Mockito.any(Message.class))).thenReturn(true);
        IMessageValidator negative = Mockito.mock(IMessageValidator.class);
        Mockito.when(negative.validate(Mockito.any(Message.class))).thenReturn(false);
        IMessageValidator validator = new ComposableMessageValidator(List.of(
            embedded,
            negative,
            embedded
        ));
        Message message = Mockito.mock(Message.class);

        boolean valid = validator.validate(message);

        Assert.assertFalse(valid);
    }
}
