package org.example;

import java.io.PrintWriter;

public class RealEstateAdFactory implements AdFactory {
    @Override
    public RealEstateAd createAd(String[] args, PrintWriter out) {
        if (args.length == 6) {
            try {
                int price = Integer.parseInt(args[4]);
                double surface = Double.parseDouble(args[5]);
                return new RealEstateAd(args[2], args[3], price, surface);
            } catch (NumberFormatException e) {
                out.println("Invalid integer value for price: " + args[3]);
            }
        } else {
            out.println("Invalid number of arguments for the <ADD real-estate> command.");
        }
        return null;
    }
}
