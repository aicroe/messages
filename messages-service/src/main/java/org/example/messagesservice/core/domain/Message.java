package org.example.messagesservice.core.domain;

import java.util.UUID;

public class Message {
    private String content;
    private UUID id;
    private MessageType type;

    public Message(UUID id, String content, MessageType type) {
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

    public MessageType getType() {
        return type;
    }

    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }

    public UUID getId() {
        return id;
    }
}
