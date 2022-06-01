package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.domain.MessageType;
import org.example.messagesservice.core.services.context.MessageServiceContext;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class MessageImageServiceTests {

    @Test
    public void createMessage_validHttpMessage_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "http://example.org/image.jpg", MessageType.Image);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }
    @Test
    public void createMessage_validHttpsMessage_returnsTrue() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "https://example.org/image.jpg", MessageType.Image);
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
    public void createMessage_emptyMessageContent_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "", MessageType.Image);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_nullMessageContent_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), null, MessageType.Image);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_messageWithSurroundingBlanks_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), " http://example.org/image.jpg ", MessageType.Image);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_messageWithMiddleBlanks_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "http://example.org/ image.jpg", MessageType.Image);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }
    
    @Test(expected = ServiceValidationException.class)
    public void createMessage_textMessage_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "hello", MessageType.Image);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }
}
