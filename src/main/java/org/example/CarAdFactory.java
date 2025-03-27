package org.example;

import java.io.PrintWriter;
import java.util.List;

/**
 * The {@code CarAdFactory} class is responsible for creating instances of {@code CarAd}.
 * It implements the {@code AdFactory} interface to provide a factory method for car advertisements.
 */
public class CarAdFactory implements AdFactory {
    /**
     * Creates a {@code CarAd} instance based on the provided arguments.
     * The expected argument format is: {@code ADD CAR <description> <location> <price> <brand>}.
     *
     * @param args The array of string arguments containing car ad details.
     * @param out The output stream to send messages or errors if needed.
     * @return A newly created {@code CarAd} instance, or {@code null} if creation fails due to invalid arguments.
     */
    @Override
    public Ad createAd(String[] args, PrintWriter out) {
        if (args.length == 6) {
            try {
                int price = Integer.parseInt(args[4]);
                return new CarAd(args[2], args[3], price, args[5]);
            } catch (NumberFormatException e) {
                out.println("Invalid integer value for price: " + args[3]);
            }
        } else {
            out.println("Invalid number of arguments for the <ADD car> command.");
        }
        return null;
    }
}
