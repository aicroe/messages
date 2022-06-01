package org.example.messagesservice.api.controller;

import org.example.messagesservice.api.exception.ApiException;
import org.example.messagesservice.api.representation.InputMessage;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.services.IMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    private IMessageService messageService;
    private ExceptionHandler<ApiException> handler;

    public MessageController(IMessageService messageService, ExceptionHandler<ApiException> handler) {
        this.messageService = messageService;
        this.handler = handler;
    }

    @GetMapping("/messages")
    public List<Message> getMessages() throws ApiException {
        return this.handler.runAndHandle(() -> messageService.getMessages());
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody InputMessage input) throws ApiException {
        return this.handler.runAndHandle(() -> {
            var message = new Message(UUID.randomUUID(), input.getContent(), input.getType());
            return messageService.createMessage(message);
        });
    }

    @GetMapping("/message/{id}")
    public Message getMessage(@PathVariable("id") UUID id) throws ApiException {
        return this.handler.runAndHandle(() -> messageService.getMessage(id));
    }

    @PutMapping("/message")
    public Message updateMessage(@RequestBody InputMessage input) throws ApiException {
        return this.handler.runAndHandle(() -> {
            var message = new Message(input.getId(), input.getContent(), input.getType());
            return messageService.updateMessage(message);
        });
    }

    @DeleteMapping("/message/{id}")
    public Message deleteMessage(@PathVariable("id") UUID id) throws ApiException {
        return this.handler.runAndHandle(() ->
            messageService.deleteMessage(id)
        );
    }
}
