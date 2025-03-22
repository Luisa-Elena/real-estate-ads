package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args, PrintWriter out, Socket clientSocket, ConcurrentHashMap<Integer, Ad> ads) {
        System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress() + " from port: " + clientSocket.getPort());
    }
}
