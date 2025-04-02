package org.example.commands;

import org.example.commands.Command;
import org.example.commands.CommandParams;
import org.example.models.Ad;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code GetAllCommand} class implements the {@code Command} interface
 * and handles retrieving all advertisements stored in the shared advertisements map.
 */
public class GetAllCommand implements Command {

    /**
     * Executes the GET-ALL command, retrieving all stored advertisements.
     *
     * @param commandParams The {@code CommandParams} object containing the output stream
     *                      and the shared advertisement map.
     */
    @Override
    public void execute(CommandParams commandParams) {
        PrintWriter out = commandParams.getOut();
        ConcurrentHashMap<Integer, Ad> ads = commandParams.getAds();

        if (ads.isEmpty()) {
            out.println("No ads available.");
        } else {
            for(Integer key : ads.keySet()) {
                out.println("--> " + key + " --> " + ads.get(key).toString());
            }
        }
    }
}
