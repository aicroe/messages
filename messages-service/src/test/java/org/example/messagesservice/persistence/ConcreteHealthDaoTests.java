package org.example.messagesservice.persistence;

import org.example.messagesservice.persistence.exception.DaoException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ConcreteHealthDaoTests {

    @Test
    public void getHealth_readWorks_returnsNotNull() throws DaoException {
        ConcreteHealthDao reader = new ConcreteHealthDao(new HealthExceptionHandler());

        var result = reader.getHealth();

        assertNotNull(result);
    }
}
