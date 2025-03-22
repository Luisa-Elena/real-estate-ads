package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class AddCommand implements Command {

    @Override
    public void execute(String[] args, PrintWriter out, Socket clientSocket, ConcurrentHashMap<Integer, Ad> ads) {
        String adType = args[1].toUpperCase();
        Ad newAd = AdRegistry.getInstance().getAdFactory(adType).createAd(args, out);

        if(newAd != null) {
            ads.compute(ads.size(), (key, value) -> newAd);
            out.println("New add inserted. --> " + newAd.toString());
        } else {
            out.println("Could not insert new add.");
        }
    }
}
