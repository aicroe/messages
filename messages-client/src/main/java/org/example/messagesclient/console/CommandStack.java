package org.example.messagesclient.console;

import org.example.messagesclient.service.AgentException;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandStack<T> {

    private Deque<Command<T>> commands;

    public CommandStack() {
        commands = new LinkedList<>();
    }

    public boolean canUndo() {
        return !commands.isEmpty();
    }

    public void defer(Command<T> command) throws AgentException {
        command.execute();
        commands.push(command);
    }

    public void undoLast() throws AgentException {
        Command<T> command = commands.pop();
        command.undo();
    }

    public void removeMatchingCommands(T payload) {
        commands.removeIf((command) -> command.matchesPayload(payload));
    }

    public List<String> getCommands() {
        var list = commands.stream()
            .map(Command::toString)
            .collect(Collectors.toList());
        Collections.reverse(list);
        return list;
    }
}
