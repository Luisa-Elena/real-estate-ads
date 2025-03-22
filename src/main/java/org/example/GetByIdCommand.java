package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class GetByIdCommand implements Command {
    @Override
    public void execute(String[] args, PrintWriter out, Socket clientSocket, ConcurrentHashMap<Integer, Ad> ads) {
        if(args.length == 2) {
            try {
                int index = Integer.parseInt(args[1]);
                if (index >= 0 && index < ads.size()) {
                    Ad ad = ads.get(index);
                    out.println(ad.toString());
                } else {
                    out.println("Index out of range.");
                }
            } catch (NumberFormatException e) {
                out.println("Invalid integer value for the index: " + args[1]);
            }
        } else {
            out.println("Invalid number of arguments for the GET-BY-ID command.");
        }
    }
}
