package org.example.commands;

import org.example.factories.AdFactory;
import org.example.registries.AdRegistry;
import org.example.models.Ad;

import java.io.PrintWriter;
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
        AdFactory adFactory = AdRegistry.getInstance().getAdFactory(adType);
        if(adFactory != null) {
            Ad newAd = adFactory.createAd(args, out);
            if(newAd != null) {
                ads.compute(ads.size(), (key, value) -> newAd);
                out.println("New add inserted. --> " + newAd.toString());
            } else {
                out.println("Could not insert new add.");
            }
        } else {
            out.println("Invalid ad type.");
        }
    }
}
