package org.example.commands;

import org.example.commands.Command;
import org.example.commands.CommandParams;

import java.net.Socket;

/**
 * The {@code ExitCommand} class implements the {@code Command} interface
 * and handles the termination of a client connection.
 * When executed, this command logs the client's disconnection details.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, logging the client's disconnection details.
     *
     * @param commandParams The {@code CommandParams} object containing the client socket
     * and other parameters (though only the client socket is used).
     */
    @Override
    public void execute(CommandParams commandParams) {
        Socket clientSocket = commandParams.getClientSocket();

        System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress() + " from port: " + clientSocket.getPort());
    }
}
