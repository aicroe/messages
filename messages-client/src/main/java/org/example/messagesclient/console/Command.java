package org.example.messagesclient.console;

import org.example.messagesclient.service.AgentException;

public interface Command<T> {
    void execute() throws AgentException;
    void undo() throws AgentException;
    boolean matchesPayload(T payload);
}
