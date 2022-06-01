package org.example.messagesclient.model;

import java.util.UUID;

public class Message {

    private String content;
    private UUID id;
    private MessageType type;

    public Message() { }

    public Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

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

    @Override
    public String toString() {
        return "OutputMessage{" +
            "content='" + content + '\'' +
            ", id=" + id +
            ", type='" + type + '\'' +
            '}';
    }
}
