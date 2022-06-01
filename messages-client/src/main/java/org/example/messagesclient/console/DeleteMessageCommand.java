package org.example.messagesclient.console;

import org.example.messagesclient.model.Message;
import org.example.messagesclient.service.Agent;
import org.example.messagesclient.service.AgentException;

import java.util.UUID;

public class DeleteMessageCommand implements Command<Message> {

    private Console console;
    private Agent agent;
    private CommandStack<Message> stack;
    private Message deletedMessage;


    public DeleteMessageCommand(Console console, Agent agent, CommandStack<Message> stack) {
        this.console = console;
        this.agent = agent;
        this.stack = stack;
    }

    @Override
    public void execute() throws AgentException {
        console.showPrompt("Enter the message id");
        String id = console.nextLine();

        deletedMessage = agent.deleteMessage(UUID.fromString(id));
        stack.removeMatchingCommands(deletedMessage);
        console.show("Message deleted: " + deletedMessage);
    }

    @Override
    public void undo() throws AgentException {
        var message = new Message(deletedMessage.getContent(), deletedMessage.getType());
        console.show("Undoing message delete");
        console.show("Message created: " + agent.createMessage(message));
    }

    @Override
    public boolean matchesPayload(Message message) {
        return message.getId().equals(deletedMessage.getId());
    }

    @Override
    public String toString() {
        return "[Delete] Message Id: " + deletedMessage.getId();
    }
}
