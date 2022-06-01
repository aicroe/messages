package org.example.messagesservice.api.representation;

import org.example.messagesservice.core.domain.MessageType;

import java.util.UUID;

public class InputMessage {

    private UUID id;
    private String content;
    private MessageType type;

    public InputMessage() { }

    public InputMessage(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    public InputMessage(UUID id, String content, MessageType type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
