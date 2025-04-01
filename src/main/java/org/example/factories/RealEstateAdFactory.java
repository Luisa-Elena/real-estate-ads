package org.example.factories;

import org.example.factories.AdFactory;
import org.example.models.RealEstateAd;

import java.io.PrintWriter;

/**
 * The {@code RealEstateAdFactory} class is responsible for creating instances of {@code RealEstateAd}.
 * It implements the {@code AdFactory} interface to provide a factory method for real estate advertisements.
 */
public class RealEstateAdFactory implements AdFactory {
    /**
     * Creates a {@code RealEstateAd} instance based on the provided arguments.
     * The expected argument format is: {@code ADD REAL-ESTATE <description> <location> <price> <surface>}.
     *
     * @param args The array of string arguments containing real estate ad details.
     * @param out The output stream to send messages or errors if needed.
     * @return A newly created {@code RealEstateAd} instance, or {@code null} if creation fails due to invalid arguments.
     */
    @Override
    public RealEstateAd createAd(String[] args, PrintWriter out) {
        if (args.length == 6) {
            try {
                int price = Integer.parseInt(args[4]);
                try {
                    double surface = Double.parseDouble(args[5]);
                    return new RealEstateAd(args[2], args[3], price, surface);
                } catch (NumberFormatException e) {
                    out.println("Invalid double value for surface: " + args[5]);
                }
            } catch (NumberFormatException e) {
                out.println("Invalid integer value for price: " + args[4]);
            }
        } else {
            out.println("Invalid number of arguments for the <ADD real-estate> command.");
        }
        return null;
    }
}
