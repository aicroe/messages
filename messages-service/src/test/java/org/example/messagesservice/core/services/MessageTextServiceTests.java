package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.domain.MessageType;
import org.example.messagesservice.core.services.context.MessageServiceContext;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class MessageTextServiceTests {

    @Test
    public void createMessage_validMessage_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "Content", MessageType.Text);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_nullMessage_throwsException() throws ServiceException {
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(null);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_emptyMessageWithEmptyStr_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "", MessageType.Text);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_emptyMessageWithNull_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), null, MessageType.Text);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_blankMessage_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "  ", MessageType.Text);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    @Test
    public void createMessage_messageWithSurroundingBlanks_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), " Hello ", MessageType.Text);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }
}
