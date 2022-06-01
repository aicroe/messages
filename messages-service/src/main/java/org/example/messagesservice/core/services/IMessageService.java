package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.services.exception.ServiceException;

import java.util.List;
import java.util.UUID;

public interface IMessageService {
    Message createMessage(Message message) throws ServiceException;
    List<Message> getMessages() throws ServiceException;
    Message getMessage(UUID uuid) throws ServiceException;
    Message deleteMessage(UUID uuid) throws ServiceException;
    Message updateMessage(Message message) throws ServiceException;
}
