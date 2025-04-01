package org.example.server;

import org.example.models.Ad;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code Server} class represents a multithreaded server application
 * that listens for client connections and handles them concurrently.
 */
class Server {
    /**
     * A thread-safe map storing advertisements, identified by unique IDs.
     */
    public static volatile ConcurrentHashMap<Integer, Ad> ads = new ConcurrentHashMap<>();

    /**
     * The main method starts the server, listens for client connections,
     * and starts new threads to handle each client.
     */
    public static void main(String[] args)
    {
        ServerSocket server = null;

        try {

            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            System.out.println("Server running...");

            while (true) {

                Socket client = server.accept();

                System.out.println("New client connected: " + client.getInetAddress().getHostAddress() + " running on port: " + client.getPort());

                ClientHandler clientHandler = new ClientHandler(client);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
