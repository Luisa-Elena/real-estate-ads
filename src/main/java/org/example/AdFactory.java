package org.example;

import java.io.PrintWriter;

public interface AdFactory {
    public Ad createAd(String[] args, PrintWriter out);
}
