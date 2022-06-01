package org.example.messagesservice.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.messagesservice.api.exception.ApiException;
import org.example.messagesservice.api.representation.InputMessage;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Message;
import org.example.messagesservice.core.domain.MessageType;
import org.example.messagesservice.core.services.IMessageService;
import org.example.messagesservice.core.services.exception.ServiceInternalException;
import org.example.messagesservice.core.services.exception.ServiceNonExistentItemException;
import org.example.messagesservice.core.services.exception.ServiceValidationException;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTests {

    @Autowired
    private MockMvc server;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IMessageService messageService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ExceptionHandler<ApiException> handlerFactory() {
            return new ControllerExceptionHandler();
        }
    }

    @Test
    public void getMessages_messagesExist_returnsList() throws Exception {
        List<Message> messages = List.of(
            new Message(UUID.randomUUID(), "hey", MessageType.Text),
            new Message(UUID.randomUUID(), "http://example.org/image.jpg", MessageType.Image));
        when(messageService.getMessages()).thenReturn(messages);
        server.perform(get("/messages"))
            .andExpect(content().string(objectMapper.writeValueAsString(messages)))
            .andExpect(status().isOk());
    }

    @Test
    public void getMessages_internalError_status5xx() throws Exception {
        when(messageService.getMessages()).thenThrow(ServiceInternalException.class);
        server.perform(get("/messages"))
            .andExpect(status().is5xxServerError());
    }

    @Test
    public void createMessage_validInput_returnsMessage() throws Exception {
        var message = new Message(UUID.randomUUID(), "ANY CONTENT", MessageType.Text);
        when(messageService.createMessage(any(Message.class))).thenReturn(message);
        server.perform(
                post("/message")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new InputMessage("ANY CONTENT", MessageType.Text)))
            )
            .andExpect(content().string(objectMapper.writeValueAsString(message)))
            .andExpect(status().isOk());
    }

    @Test
    public void createMessage_validationError_badRequest() throws Exception {
        when(messageService.createMessage(any(Message.class))).thenThrow(ServiceValidationException.class);
        server.perform(
                post("/message")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new InputMessage("ANY CONTENT", MessageType.Text)))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createMessage_internalError_status5xx() throws Exception {
        when(messageService.createMessage(any(Message.class))).thenThrow(ServiceInternalException.class);
        server.perform(
                post("/message")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new InputMessage("ANY CONTENT", MessageType.Text)))
            )
            .andExpect(status().is5xxServerError());
    }

    @Test
    public void getMessage_validId_returnsMessage() throws Exception {
        var id = UUID.randomUUID();
        var message = new Message(id, "CONTENT", MessageType.Text);
        when(messageService.getMessage(any(UUID.class))).thenReturn(message);
        server.perform(get("/message/" + id))
            .andExpect(content().string(objectMapper.writeValueAsString(message)))
            .andExpect(status().isOk());
    }

    @Test
    public void getMessage_notFound_returns404() throws Exception {
        var id = UUID.randomUUID();
        when(messageService.getMessage(any(UUID.class))).thenThrow(ServiceNonExistentItemException.class);
        server.perform(get("/message/" + id))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getMessage_invalidId_returnsBadRequest() throws Exception {
        var id = "fabqwer123";
        when(messageService.getMessage(any(UUID.class))).thenThrow(ServiceNonExistentItemException.class);
        server.perform(get("/message/" + id))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateMessage_validInput_returnsNewMessage() throws Exception {
        var id = UUID.randomUUID();
        var message = new Message(id, "UPDATED CONTENT", MessageType.Short);
        when(messageService.updateMessage(any(Message.class))).thenReturn(message);
        server.perform(
                put("/message")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new InputMessage(id, "UPDATED CONTENT", MessageType.Short)))
            )
            .andExpect(content().string(objectMapper.writeValueAsString(message)))
            .andExpect(status().isOk());
    }

    @Test
    public void updateMessage_notFound_returns404() throws Exception {
        when(messageService.updateMessage(any(Message.class))).thenThrow(ServiceNonExistentItemException.class);
        server.perform(
                put("/message")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new InputMessage(UUID.randomUUID(), "UPDATED CONTENT", MessageType.Text)))
            )
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMessage_invalidInput_returnsBadRequest() throws Exception {
        when(messageService.updateMessage(any(Message.class))).thenThrow(ServiceValidationException.class);
        server.perform(
                put("/message")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new InputMessage(UUID.randomUUID(), "", MessageType.Text)))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteMessage_validId_returnsMessage() throws Exception {
        var id = UUID.randomUUID();
        var message = new Message(id, "CONTENT", MessageType.Short);
        when(messageService.deleteMessage(any(UUID.class))).thenReturn(message);
        server.perform(delete("/message/" + id))
            .andExpect(content().string(objectMapper.writeValueAsString(message)))
            .andExpect(status().isOk());
    }

    @Test
    public void deleteMessage_notFound_returns404() throws Exception {
        var id = UUID.randomUUID();
        when(messageService.deleteMessage(any(UUID.class))).thenThrow(ServiceNonExistentItemException.class);
        server.perform(delete("/message/" + id))
            .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMessage_invalidId_returnsBadRequest() throws Exception {
        var id = "fabqwer123";
        when(messageService.getMessage(any(UUID.class))).thenThrow(ServiceNonExistentItemException.class);
        server.perform(delete("/message/" + id))
            .andExpect(status().isBadRequest());
    }
}
