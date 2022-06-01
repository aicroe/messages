package org.example.messagesservice.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.persistence.exception.DaoException;

public class ConcreteHealthDao implements HealthDao {

    private Health cached;
    private ExceptionHandler<DaoException> handler;

    public ConcreteHealthDao(ExceptionHandler<DaoException> handler) {
        this.handler = handler;
    }

    @Override
    public Health getHealth() throws DaoException {
        if (cached != null) {
            return cached;
        }

        var stream = getClass().getClassLoader().getResourceAsStream("health.json");
        assert stream != null;
        return this.handler.runAndHandle(() -> {
            cached = new ObjectMapper().readValue(stream, Health.class);
            return cached;
        });
    }
}
