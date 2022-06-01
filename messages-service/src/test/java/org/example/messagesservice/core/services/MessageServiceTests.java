package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.domain.MessageType;
import org.example.messagesservice.core.services.context.MessageServiceContext;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceNonExistentItemException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;
import org.example.messagesservice.persistence.exception.DaoNonExistentItemException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MessageServiceTests {

    @Test
    public void getMessages_noneSaved_returnsEmpty() throws ServiceException {
        MessageService messageService = new MessageServiceContext().mockGetMessages(List.of()).getMessageService();

        List<Message> messages = messageService.getMessages();

        Assert.assertEquals(messages, Collections.EMPTY_LIST);
    }

    @Test
    public void getMessages_thereAreElementsSaved_returnsList() throws ServiceException {
        List<Message> mockedMessages = List.of(
            Mockito.mock(Message.class),
            Mockito.mock(Message.class),
            Mockito.mock(Message.class));
        MessageService messageService = new MessageServiceContext()
            .mockGetMessages(mockedMessages)
            .getMessageService();

        List<Message> messages = messageService.getMessages();

        Assert.assertEquals(messages, mockedMessages);
    }

    @Test
    public void getMessage_foundByUuid_returnsMessage() throws ServiceException {
        Message message = Mockito.mock(Message.class);
        MessageService messageService = new MessageServiceContext().mockGetMessage(message).getMessageService();

        Message result = messageService.getMessage(UUID.randomUUID());

        Assert.assertEquals(result, message);
    }

    @Test(expected = ServiceNonExistentItemException.class)
    public void getMessage_notFound_throwsException() throws ServiceException {
        MessageService messageService = new MessageServiceContext()
            .mockGetMessage(DaoNonExistentItemException.class)
            .getMessageService();

        messageService.getMessage(UUID.randomUUID());
    }

    @Test(expected = ServiceValidationException.class)
    public void getMessage_nullUuid_throwsException() throws ServiceException {
        MessageService messageService = new MessageServiceContext()
            .mockDeleteMessage(Mockito.mock(Message.class))
            .getMessageService();

        messageService.getMessage(null);
    }

    @Test
    public void deleteMessage_foundByUuid_returnsMessage() throws ServiceException {
        Message message = Mockito.mock(Message.class);
        MessageService messageService = new MessageServiceContext().mockDeleteMessage(message).getMessageService();

        Message result = messageService.deleteMessage(UUID.randomUUID());

        Assert.assertEquals(result, message);
    }

    @Test(expected = ServiceNonExistentItemException.class)
    public void deleteMessage_notFound_throwsException() throws ServiceException {
        MessageService messageService = new MessageServiceContext()
            .mockDeleteMessage(DaoNonExistentItemException.class)
            .getMessageService();

        messageService.deleteMessage(UUID.randomUUID());
    }

    @Test(expected = ServiceValidationException.class)
    public void deleteMessage_nullUuid_throwsException() throws ServiceException {
        var context = new MessageServiceContext().mockDeleteMessage(Mockito.mock(Message.class));
        MessageService messageService = context.getMessageService();

        messageService.deleteMessage(null);
    }

    @Test
    public void updateMessage_existingMessage_returnsMessage() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "content", MessageType.Text);
        MessageService messageService = new MessageServiceContext().mockUpdateMessage(message).getMessageService();

        Message result = messageService.updateMessage(message);

        Assert.assertEquals(result, message);
    }

    @Test(expected = ServiceValidationException.class)
    public void updateMessage_nonValidMessage_returnsMessage() throws ServiceException {
        Message message = new Message(UUID.randomUUID(), "", MessageType.Text);
        MessageService messageService = new MessageServiceContext().mockUpdateMessage(message).getMessageService();

        messageService.updateMessage(message);
    }

    @Test(expected = ServiceNonExistentItemException.class)
    public void updateMessage_notFound_throwsException() throws ServiceException {
        MessageService messageService = new MessageServiceContext()
            .mockUpdateMessage(DaoNonExistentItemException.class)
            .getMessageService();

        messageService.updateMessage(new Message(UUID.randomUUID(), "content", MessageType.Text));
    }

    @Test(expected = ServiceValidationException.class)
    public void updateMessage_nullMessage_throwsException() throws ServiceException {
        MessageService messageService = new MessageServiceContext()
            .mockUpdateMessage(Mockito.mock(Message.class))
            .getMessageService();

        messageService.updateMessage(null);
    }
}
