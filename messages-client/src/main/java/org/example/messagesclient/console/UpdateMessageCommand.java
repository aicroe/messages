package org.example.messagesclient.console;

import org.example.messagesclient.model.Message;
import org.example.messagesclient.service.Agent;
import org.example.messagesclient.service.AgentException;

import java.util.UUID;

public class UpdateMessageCommand implements Command<Message> {

    private Console console;
    private Agent agent;
    private Message originalMessage;

    public UpdateMessageCommand(Console console, Agent agent) {
        this.console = console;
        this.agent = agent;
    }

    @Override
    public void execute() throws AgentException {
        console.showPrompt("Enter the message id");
        UUID id = UUID.fromString(console.nextLine());
        console.showPrompt("Enter the new message content");
        String content = console.nextLine();

        originalMessage = agent.getMessage(id);
        console.show("Message updated: " + agent.updateMessage(new Message(id, content, originalMessage.getType())));
    }

    @Override
    public void undo() throws AgentException {
        console.show("Undoing message update");
        console.show("Message updated: " + agent.updateMessage(originalMessage));
    }

    @Override
    public boolean matchesPayload(Message message) {
        return message.getId().equals(originalMessage.getId());
    }

    @Override
    public String toString() {
        return "[Update] Message Id: " + originalMessage.getId();
    }
}
