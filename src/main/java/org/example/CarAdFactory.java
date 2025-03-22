package org.example;

import java.io.PrintWriter;

public class CarAdFactory implements AdFactory {
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
