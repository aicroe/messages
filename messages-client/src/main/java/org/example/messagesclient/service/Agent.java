package org.example.messagesclient.service;

import org.example.messagesclient.model.Health;
import org.example.messagesclient.model.Message;

import java.util.List;
import java.util.UUID;

public interface Agent {
    List<Message> getMessages() throws AgentException;
    Message createMessage(Message message) throws AgentException;
    Message getMessage(UUID id) throws AgentException;
    Message updateMessage(Message message) throws AgentException;
    Message deleteMessage(UUID id) throws AgentException;
    Health getHealth() throws AgentException;
}
