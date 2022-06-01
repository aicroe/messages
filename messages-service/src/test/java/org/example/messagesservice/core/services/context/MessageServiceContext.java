package org.example.messagesservice.core.services.context;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.services.MessageService;
import org.example.messagesservice.core.services.ServiceExceptionHandler;
import org.example.messagesservice.persistence.IMessageDao;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageServiceContext {

    private IMessageDao messageDao;
    private ContextExceptionHandler handler;

    public MessageServiceContext() {
        messageDao = mock(IMessageDao.class);
        handler = new ContextExceptionHandler();
    }

    public MessageServiceContext mockCreateMessage() {
        handler.catchAndLog(() -> when(messageDao.createMessage(any(Message.class))).thenReturn(mock(Message.class)));
        return this;
    }

    public MessageServiceContext mockGetMessages(List<Message> messages) {
        handler.catchAndLog(() -> when(messageDao.getMessages()).thenReturn(messages));
        return this;
    }

    public MessageServiceContext mockGetMessage(Message message) {
        handler.catchAndLog(() -> when(messageDao.getMessage(any(UUID.class))).thenReturn(message));
        return this;
    }

    public MessageServiceContext mockGetMessage(Class<? extends Exception> throwable) {
        handler.catchAndLog(() -> when(messageDao.getMessage(any(UUID.class))).thenThrow(throwable));
        return this;
    }

    public MessageServiceContext mockDeleteMessage(Message message) {
        handler.catchAndLog(() -> when(messageDao.deleteMessage(any(UUID.class))).thenReturn(message));
        return this;
    }

    public MessageServiceContext mockDeleteMessage(Class<? extends Exception> throwable) {
        handler.catchAndLog(() -> when(messageDao.deleteMessage(any(UUID.class))).thenThrow(throwable));
        return this;
    }

    public MessageServiceContext mockUpdateMessage(Message message) {
        handler.catchAndLog(() -> when(messageDao.updateMessage(any(Message.class))).thenReturn(message));
        return this;
    }

    public MessageServiceContext mockUpdateMessage(Class<? extends Exception> throwable) {
        handler.catchAndLog(() -> when(messageDao.updateMessage(any(Message.class))).thenThrow(throwable));
        return this;
    }

    public MessageService getMessageService() {
        return new MessageService(messageDao, new ServiceExceptionHandler());
    }
}
