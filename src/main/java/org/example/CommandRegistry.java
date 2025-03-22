package org.example;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static CommandRegistry instance = null;
    private final Map<String, Command> commandMap = new HashMap<>();

    private CommandRegistry() {
        initializeCommands();
    }

    public static synchronized CommandRegistry getInstance() {
        if (instance == null) {
            instance = new CommandRegistry();
        }
        return instance;
    }

    private void initializeCommands() {
        commandMap.put("EXIT", new ExitCommand());
        commandMap.put("ADD", new AddCommand());
        commandMap.put("GET-BY-ID", new GetByIdCommand());
        commandMap.put("GET-ALL", new GetAllCommand());
    }

    public Command getCommand(String action) {
        return commandMap.get(action);
    }
}
