package org.example.messagesclient.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.messagesclient.model.Health;
import org.example.messagesclient.model.Message;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

public class MessageAgent implements Agent {

    private Properties props;
    private HttpClientFacade http;
    private final Type listType = new TypeToken<List<Message>>(){}.getType();

    public MessageAgent(Properties props, HttpClientFacade http) {
        this.props = props;
        this.http = http;
    }

    @Override
    public List<Message> getMessages() throws AgentException {
        var response = http.get(String.format("%s/messages", props.getBaseUrl()));
        return new Gson().fromJson(response, listType);
    }

    @Override
    public Message createMessage(Message message) throws AgentException {
        var response = http.post(String.format("%s/message", props.getBaseUrl()), new Gson().toJson(message));
        return new Gson().fromJson(response, Message.class);
    }

    @Override
    public Message getMessage(UUID id) throws AgentException {
        var response = http.get(String.format("%s/message/%s", props.getBaseUrl(), id));
        return new Gson().fromJson(response, Message.class);
    }

    @Override
    public Message updateMessage(Message message) throws AgentException {
        var response = http.put(String.format("%s/message", props.getBaseUrl()), new Gson().toJson(message));
        return new Gson().fromJson(response, Message.class);
    }

    @Override
    public Message deleteMessage(UUID id) throws AgentException {
        var response = http.delete(String.format("%s/message/%s", props.getBaseUrl(), id));
        return new Gson().fromJson(response, Message.class);
    }

    @Override
    public Health getHealth() throws AgentException {
        var response = http.get(String.format("%s/health", props.getBaseUrl()));
        return new Gson().fromJson(response, Health.class);
    }
}
