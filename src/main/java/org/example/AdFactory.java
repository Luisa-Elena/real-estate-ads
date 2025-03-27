package org.example;

import java.io.PrintWriter;
import java.util.List;

/**
 * The {@code AdFactory} interface defines a contract for creating advertisements.
 * Implementing classes should provide a method to create specific types of ads.
 */
public interface AdFactory {
    /**
     * Creates an advertisement based on the provided arguments.
     *
     * @param args The array of string arguments containing ad details.
     * @param out The output stream to send messages or errors if needed.
     * @return A newly created {@code Ad} instance, or {@code null} if creation fails.
     */
    public Ad createAd(String[] args, PrintWriter out);
}
