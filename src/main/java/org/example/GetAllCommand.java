package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class GetAllCommand implements Command {
    @Override
    public void execute(String[] args, PrintWriter out, Socket clientSocket, ConcurrentHashMap<Integer, Ad> ads) {
        if (ads.isEmpty()) {
            out.println("No ads available.");
        } else {
            for(Integer key : ads.keySet()) {
                out.println("--> " + key + " --> " + ads.get(key).toString());
            }
        }
    }
}
