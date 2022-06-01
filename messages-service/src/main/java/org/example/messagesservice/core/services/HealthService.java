package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.core.services.exception.ServiceException;

public interface HealthService {
    Health getHealth() throws ServiceException;
}
