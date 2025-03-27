package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CommandRegistry} class is a singleton that manages a registry of commands.
 * It provides a mechanism to retrieve command instances based on their string identifiers.
 */
public class CommandRegistry {
    private static CommandRegistry instance = null;
    private final Map<String, Command> commandMap = new HashMap<>();

    /**
     * Private constructor to enforce the singleton pattern and initialize commands.
     */
    private CommandRegistry() {
        initializeCommands();
    }

    /**
     * Returns the singleton instance of the {@code CommandRegistry}.
     * Ensures thread safety by synchronizing the instance creation.
     *
     * @return The singleton instance of {@code CommandRegistry}.
     */
    public static synchronized CommandRegistry getInstance() {
        if (instance == null) {
            instance = new CommandRegistry();
        }
        return instance;
    }

    /**
     * Initializes the registry with predefined command mappings.
     */
    private void initializeCommands() {
        commandMap.put("EXIT", new ExitCommand());
        commandMap.put("ADD", new AddCommand());
        commandMap.put("GET-BY-ID", new GetByIdCommand());
        commandMap.put("GET-ALL", new GetAllCommand());
    }

    /**
     * Retrieves a command instance based on the given action string.
     *
     * @param action The string identifier of the command.
     * @return The corresponding {@code Command} instance, or {@code null} if not found.
     */
    public Command getCommand(String action) {
        return commandMap.get(action);
    }
}
