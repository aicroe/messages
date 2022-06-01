package org.example.messagesservice.core.services;

import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.persistence.HealthDao;

public class ConcreteHealthService implements HealthService {

    private HealthDao healthDao;
    private ExceptionHandler<ServiceException> handler;

    public ConcreteHealthService(HealthDao healthDao, ExceptionHandler<ServiceException> handler) {
        this.healthDao = healthDao;
        this.handler = handler;
    }

    @Override
    public Health getHealth() throws ServiceException {
        return this.handler.runAndHandle(() -> healthDao.getHealth());
    }
}
