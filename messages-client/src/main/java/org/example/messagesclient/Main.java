package org.example.messagesclient;

import org.example.messagesclient.console.CommandStack;
import org.example.messagesclient.console.Console;
import org.example.messagesclient.console.Controller;
import org.example.messagesclient.console.ConsoleController;
import org.example.messagesclient.model.Message;
import org.example.messagesclient.service.Agent;
import org.example.messagesclient.service.HttpClientFacade;
import org.example.messagesclient.service.MessageAgent;
import org.example.messagesclient.service.Properties;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CommandStack<Message> stack = new CommandStack<>();
        Agent agent = new MessageAgent(new Properties("http://localhost:8080"), new HttpClientFacade());

        Console console = new Console(in);
        Controller controller = new ConsoleController(console, stack, agent);
        console.setController(controller);

        console.run();
    }
}
