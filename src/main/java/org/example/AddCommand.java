package org.example;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/**
 * The {@code AddCommand} class implements the {@code Command} interface
 * to handle the addition of new advertisements. It retrieves an ad factory
 * from the {@code AdRegistry}, creates a new ad, and stores it in the shared
 * advertisement map.
 */
public class AddCommand implements Command {

    /**
     * Executes the ADD command, creating and inserting a new advertisement.
     *
     * @param commandParams The {@code CommandParams} object containing command arguments,
     *                      the output stream, and the shared advertisement map.
     */
    @Override
    public void execute(CommandParams commandParams) {
        String[] args = commandParams.getArgs();
        PrintWriter out = commandParams.getOut();
        ConcurrentHashMap<Integer, Ad> ads = commandParams.getAds();

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
