package org.example.messagesclient.console;

import org.example.messagesclient.model.Message;
import org.example.messagesclient.service.Agent;
import org.example.messagesclient.service.AgentException;

import java.util.UUID;
import java.util.stream.IntStream;

public class ConsoleController implements Controller {

    private Console console;
    private CommandStack<Message> stack;
    private Agent agent;

    public ConsoleController(Console console, CommandStack<Message> stack, Agent agent) {
        this.console = console;
        this.stack = stack;
        this.agent = agent;
    }

    @Override
    public void performAction(String action) {
        switch (action) {
            case ActionType.HELP -> console.showMenu();
            case ActionType.GET_MESSAGES -> handle(() -> agent.getMessages().forEach(message -> console.show(message)));
            case ActionType.CREATE_MESSAGE -> handle(() -> stack.defer(new CreateMessageCommand(console, agent)));
            case ActionType.GET_MESSAGE -> handle(this::getMessage);
            case ActionType.UPDATE_MESSAGE -> handle(() -> stack.defer(new UpdateMessageCommand(console, agent)));
            case ActionType.DELETE_MESSAGE -> handle(() -> stack.defer(new DeleteMessageCommand(console, agent, stack)));
            case ActionType.UNDO -> handle(this::undo);
            case ActionType.HEALTH -> handle(() -> console.show(agent.getHealth()));
            case ActionType.HISTORY -> showHistory();
            case ActionType.EXIT -> console.setRunning(false);
            default -> {
                console.show(String.format("Unknown action '%s'", action));
            }
        }
    }

    private void getMessage() throws AgentException {
        console.showPrompt("Enter the message id");
        String id = console.nextLine();
        Message message = agent.getMessage(UUID.fromString(id));
        console.show(message);
    }

    private void undo() throws AgentException {
        if (stack.canUndo()) {
            stack.undoLast();
        } else {
            console.show("Actions stack empty: There are not actions to undo");
        }
    }

    private void showHistory() {
        var history = stack.getCommands();
        IntStream.range(0, history.size())
            .boxed()
            .forEachOrdered(index -> console.show((index + 1) + ") " + history.get(index)));
    }

    private void handle(Callback callback) {
        try {
            callback.call();
        } catch (Exception e) {
            console.show(e);
        }
    }

    @FunctionalInterface
    private interface Callback {
        void call() throws Exception;
    }
}
