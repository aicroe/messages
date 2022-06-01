package org.example.messagesservice.api.controller;

import org.example.messagesservice.api.exception.ApiException;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.services.*;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.persistence.ConcreteHealthDao;
import org.example.messagesservice.persistence.HealthExceptionHandler;
import org.example.messagesservice.persistence.InMemoryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersProvider {

    @Bean
    public ExceptionHandler<ServiceException> getServiceHandler() {
        return new ServiceExceptionHandler();
    }

    @Bean
    public IMessageService getMessageService() {
        return new MessageService(new InMemoryDao(), getServiceHandler());
    }

    @Bean
    public ExceptionHandler<ApiException> getApiHandler() {
        return new ControllerExceptionHandler();
    }

    @Bean
    public HealthService getHealthService() {
        return new ConcreteHealthService(new ConcreteHealthDao(new HealthExceptionHandler()), getServiceHandler());
    }
}
