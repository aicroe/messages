package org.example.messagesservice.core.services.context;

import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.core.services.ConcreteHealthService;
import org.example.messagesservice.core.services.HealthService;
import org.example.messagesservice.core.services.ServiceExceptionHandler;
import org.example.messagesservice.persistence.HealthDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HealthServiceContext {

    private HealthDao healthDao;
    private ContextExceptionHandler handler;

    public HealthServiceContext() {
        healthDao = mock(HealthDao.class);
        handler = new ContextExceptionHandler();
    }

    public HealthServiceContext mockGetHealth(Health health) {
        this.handler.catchAndLog(() -> when(healthDao.getHealth()).thenReturn(health));
        return this;
    }

    public HealthServiceContext mockGetHealth(Class<? extends Exception> throwable) {
        this.handler.catchAndLog(() -> when(healthDao.getHealth()).thenThrow(throwable));
        return this;
    }

    public HealthService getHealthService() {
        return new ConcreteHealthService(healthDao, new ServiceExceptionHandler());
    }
}
