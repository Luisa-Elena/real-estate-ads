package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public interface Command {
    public void execute(String[] args, PrintWriter out, Socket clientSocket, ConcurrentHashMap<Integer, Ad> ads);
}
