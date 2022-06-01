package org.example.messagesservice.persistence;

import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.persistence.exception.DaoDuplicatedKeyException;
import org.example.messagesservice.persistence.exception.DaoException;
import org.example.messagesservice.persistence.exception.DaoNonExistentItemException;

import java.util.*;

public class InMemoryDao implements IMessageDao {

    private Map<UUID, Message> store;

    public InMemoryDao() {
        store = new HashMap<>();
    }

    @Override
    public Message createMessage(Message message) throws DaoException {
        if (store.containsKey(message.getId())) {
            throw new DaoDuplicatedKeyException("Message already exists");
        }
        store.put(message.getId(), message);
        return message;
    }

    @Override
    public List<Message> getMessages() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Message getMessage(UUID uuid) throws DaoException {
        var message = store.get(uuid);
        if (message == null) {
            throw new DaoNonExistentItemException("Message not found");
        }
        return message;
    }

    @Override
    public Message deleteMessage(UUID uuid) throws DaoException {
        var message = store.remove(uuid);
        if (message == null) {
            throw new DaoNonExistentItemException("Message not found, cannot delete");
        }
        return message;
    }

    @Override
    public Message updateMessage(Message message) throws DaoException {
        if (!store.containsKey(message.getId())) {
            throw new DaoNonExistentItemException("Message not found, cannot update");
        }
        store.replace(message.getId(), message);
        return message;
    }
}
