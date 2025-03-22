package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final CommandRegistry commandRegistry;

    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
        this.commandRegistry = CommandRegistry.getInstance();
    }

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
                    command.execute(parts, out, clientSocket, Server.ads);
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
