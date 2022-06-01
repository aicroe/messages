package org.example.messagesservice.core.services;

import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;
import org.example.messagesservice.core.services.validation.IMessageValidator;
import org.example.messagesservice.persistence.IMessageDao;

import java.util.List;
import java.util.UUID;

public class MessageService implements IMessageService {

    private IMessageDao messageDao;
    private ExceptionHandler<ServiceException> handler;

    public MessageService(IMessageDao messageDao, ExceptionHandler<ServiceException> handler) {
        this.messageDao = messageDao;
        this.handler = handler;
    }

    @Override
    public Message createMessage(Message message) throws ServiceException {
        checkNull(message, "Cannot save null message");

        IMessageValidator validator = IMessageValidator.getValidator(message.getType());

        validateMessage(validator, message, "Cannot save invalid message");

        return handler.runAndHandle(() -> messageDao.createMessage(message));
    }

    @Override
    public List<Message> getMessages() throws ServiceException {
        return handler.runAndHandle(() -> messageDao.getMessages());
    }

    @Override
    public Message getMessage(UUID uuid) throws ServiceException {
        checkNull(uuid, "UUID cannot be null");

        return handler.runAndHandle(() -> messageDao.getMessage(uuid));
    }

    @Override
    public Message deleteMessage(UUID uuid) throws ServiceException {
        checkNull(uuid, "UUID cannot be null");

        return handler.runAndHandle(() -> messageDao.deleteMessage(uuid));
    }

    @Override
    public Message updateMessage(Message message) throws ServiceException {
        checkNull(message, "Cannot update null message");

        IMessageValidator validator = IMessageValidator.getValidator(message.getType());

        validateMessage(validator, message, "Cannot update invalid message");

        return handler.runAndHandle(() -> messageDao.updateMessage(message));
    }

    private <T> void checkNull(T value, String message) throws ServiceValidationException {
        if (value == null) {
            throw new ServiceValidationException(message);
        }
    }

    private void validateMessage(IMessageValidator validator, Message message, String s) throws ServiceValidationException {
        if (!validator.validate(message)) {
            throw new ServiceValidationException(s);
        }
    }
}
