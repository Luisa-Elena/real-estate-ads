package org.example.commands;

import org.example.models.Ad;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code CommandParams} class serves as a container for parameters
 * passed to command executions. It encapsulates commonly used resources
 * such as client input arguments, output stream, client socket, and the shared
 * advertisement map.
 */
public class CommandParams {
    private final String[] args;
    private final PrintWriter out;
    private final Socket clientSocket;
    private final ConcurrentHashMap<Integer, Ad> ads;

    public CommandParams(String[] args, PrintWriter out, Socket clientSocket, ConcurrentHashMap<Integer, Ad> ads) {
        this.args = args;
        this.out = out;
        this.clientSocket = clientSocket;
        this.ads = ads;
    }

    public String[] getArgs() {
        return args;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public ConcurrentHashMap<Integer, Ad> getAds() {
        return ads;
    }
}
