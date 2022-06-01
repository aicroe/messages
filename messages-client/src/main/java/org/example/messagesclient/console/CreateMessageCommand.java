package org.example.messagesclient.console;

import org.example.messagesclient.model.Message;
import org.example.messagesclient.model.MessageType;
import org.example.messagesclient.service.Agent;
import org.example.messagesclient.service.AgentException;

public class CreateMessageCommand implements Command<Message> {

    private Console console;
    private Agent agent;
    private Message createdMessage;

    public CreateMessageCommand(Console console, Agent agent) {
        this.console = console;
        this.agent = agent;
    }

    @Override
    public void execute() throws AgentException {
        console.showPrompt("Enter the message type (Text, Image, Short)");
        String type = console.nextLine();
        console.showPrompt("Enter the message content");
        String content = console.nextLine();

        createdMessage = agent.createMessage(new Message(content, MessageType.valueOf(type)));
        console.show("Message created: " + createdMessage);
    }

    @Override
    public void undo() throws AgentException {
        console.show("Undoing message create");
        console.show("Message deleted: " + agent.deleteMessage(createdMessage.getId()));
    }

    @Override
    public boolean matchesPayload(Message message) {
        return message.getId().equals(createdMessage.getId());
    }

    @Override
    public String toString() {
        return "[Create] Message Id: " + createdMessage.getId();
    }
}
