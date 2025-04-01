package org.example.commands;

import org.example.commands.Command;
import org.example.commands.CommandParams;
import org.example.models.Ad;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The {@code GetByIdCommand} class implements the {@code Command} interface
 * and retrieves a specific advertisement by its ID.
 */
public class GetByIdCommand implements Command {

    /**
     * Executes the GET-BY-ID command, retrieving an advertisement by its ID.
     *
     * @param commandParams The {@code CommandParams} object containing command arguments,
     *                      output stream, and the shared advertisement map.
     */
    @Override
    public void execute(CommandParams commandParams) {
        String[] args = commandParams.getArgs();
        PrintWriter out = commandParams.getOut();
        ConcurrentHashMap<Integer, Ad> ads = commandParams.getAds();

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
