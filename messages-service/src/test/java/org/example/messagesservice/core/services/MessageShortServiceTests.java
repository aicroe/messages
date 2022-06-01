package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.domain.MessageType;
import org.example.messagesservice.core.services.context.MessageServiceContext;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

public class MessageShortServiceTests {

    @Test
    public void createMessage_nullMessageContent_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), null, MessageType.Short);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }

    @Test()
    public void createMessage_emptyMessageContent_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "", MessageType.Short);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }

    @Test()
    public void createMessage_messageWithSpacesInBetween_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "Hi Jhon, hope you are doing fine.", MessageType.Short);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }

    @Test
    public void createMessage_messageWithOpeningAndEndingSpaces_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), " example.org ", MessageType.Short);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }

    @Test
    public void createMessage_messageLength280Chars_returnsNotNull() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), getAsterisks(280), MessageType.Short);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        Message result = messageService.createMessage(message);

        Assert.assertNotNull(result);
    }

    @Test(expected = ServiceValidationException.class)
    public void createMessage_messageLongerThan280Chars_throwsException() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), getAsterisks(281), MessageType.Short);
        IMessageService messageService = new MessageServiceContext().mockCreateMessage().getMessageService();

        messageService.createMessage(message);
    }

    private String getAsterisks(int size) {
        char[] chars = new char[size];
        Arrays.fill(chars, '*');
        return new String(chars);
    }
}
