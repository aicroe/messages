package org.example.messagesclient.console;

import java.util.Scanner;

public class Console {

    private boolean running;
    private Scanner in;
    private Controller controller;

    public Console(Scanner in) {
        this.in = in;
    }

    public void run() {
        running = true;
        showMenu();
        while (running) {
            showPrompt();
            String action = nextLine();
            controller.performAction(action);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void showMenu() {
        System.out.println(String.format("Enter '%s' to show the available actions", ActionType.HELP));
        System.out.println(String.format("Enter '%s' to get the messages list", ActionType.GET_MESSAGES));
        System.out.println(String.format("Enter '%s' to create a message", ActionType.CREATE_MESSAGE));
        System.out.println(String.format("Enter '%s' to get a message", ActionType.GET_MESSAGE));
        System.out.println(String.format("Enter '%s' to update a message", ActionType.UPDATE_MESSAGE));
        System.out.println(String.format("Enter '%s' to delete a message", ActionType.DELETE_MESSAGE));
        System.out.println(String.format("Enter '%s' to undo the last action", ActionType.UNDO));
        System.out.println(String.format("Enter '%s' to show the service health", ActionType.HEALTH));
        System.out.println(String.format("Enter '%s' to show the history", ActionType.HISTORY));
        System.out.println(String.format("Enter '%s' to exit", ActionType.EXIT));
    }

    public void showPrompt() {
        System.out.print("> ");
    }

    public void showPrompt(String message) {
        System.out.print(String.format("%s > ", message));
    }

    public void show(Object message) {
        System.out.println(message);
    }

    public String nextLine() {
        return in.nextLine().trim();
    }
}
