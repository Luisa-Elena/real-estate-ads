package org.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

class Server {
    public static ConcurrentHashMap<Integer, Ad> ads = new ConcurrentHashMap<>();

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
