package org.example.messagesservice.core.services;

import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.core.services.context.HealthServiceContext;
import org.example.messagesservice.core.services.exception.ServiceException;
import org.example.messagesservice.core.services.exception.ServiceInternalException;
import org.example.messagesservice.persistence.exception.DaoInternalException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcreteHealthServiceTests {

    @Test
    public void getHealth_cached_healthMatches() throws ServiceException {
        var health = new Health("message-service", "FINE", "0.0.0");
        var healthService = new HealthServiceContext()
            .mockGetHealth(health)
            .getHealthService();

        var result = healthService.getHealth();

        assertEquals(health, result);
    }

    @Test(expected = ServiceInternalException.class)
    public void getHealth_internalError_throwsException() throws ServiceException {
        var healthService = new HealthServiceContext()
            .mockGetHealth(DaoInternalException.class)
            .getHealthService();

        healthService.getHealth();
    }
}
