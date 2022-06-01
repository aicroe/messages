package org.example.messagesservice.api.controller;

import org.example.messagesservice.api.exception.ApiException;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.core.services.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private ExceptionHandler<ApiException> handler;
    private HealthService healthService;

    public HealthController(HealthService healthService, ExceptionHandler<ApiException> handler) {
        this.healthService = healthService;
        this.handler = handler;
    }

    @GetMapping("/health")
    public Health getHealth() throws ApiException {
        return this.handler.runAndHandle(() -> this.healthService.getHealth());
    }
}
