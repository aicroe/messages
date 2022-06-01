package org.example.messagesservice.persistence;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.persistence.exception.DaoException;

import java.util.List;
import java.util.UUID;

public interface IMessageDao {
    Message createMessage(Message message) throws DaoException;
    List<Message> getMessages() throws DaoException;
    Message getMessage(UUID uuid) throws DaoException;
    Message deleteMessage(UUID uuid) throws DaoException;
    Message updateMessage(Message message) throws DaoException;
}
