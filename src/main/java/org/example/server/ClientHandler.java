package org.example.server;

import org.example.commands.Command;
import org.example.commands.CommandParams;
import org.example.registries.CommandRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The {@code ClientHandler} class handles client requests in a separate thread.
 * It processes commands received from the client and sends appropriate responses.
 */
class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final CommandRegistry commandRegistry;

    /**
     * Constructs a {@code ClientHandler} with the specified client socket.
     *
     * @param socket The client socket.
     */
    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
        this.commandRegistry = CommandRegistry.getInstance();
    }

    /**
     * Runs the client handler, processing input from the client and executing commands.
     * It listens for client messages, looks up corresponding commands, and executes them.
     */
    public void run()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {

                String parts[] = line.split(" ");
                String action = parts[0].toUpperCase();

                Command command = commandRegistry.getCommand(action);
                if(command == null) {
                    out.println("Invalid command.");
                } else {
                    CommandParams params = new CommandParams(parts, out, clientSocket, Server.ads);
                    command.execute(params);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
