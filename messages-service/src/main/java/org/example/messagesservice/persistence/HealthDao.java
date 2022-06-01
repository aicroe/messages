package org.example.messagesservice.persistence;

import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.persistence.exception.DaoException;

public interface HealthDao {
    Health getHealth() throws DaoException;
}
