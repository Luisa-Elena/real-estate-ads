package org.example;

import java.util.HashMap;
import java.util.Map;

public class AdRegistry {
    private static AdRegistry instance = null;
    private final Map<String, AdFactory> adMap = new HashMap<>();

    private AdRegistry() {
        initializeAds();
    }

    public static synchronized AdRegistry getInstance() {
        if (instance == null) {
            instance = new AdRegistry();
        }
        return instance;
    }

    private void initializeAds() {
        adMap.put("REAL-ESTATE", new RealEstateAdFactory());
        adMap.put("CAR", new CarAdFactory());
    }

    public AdFactory getAdFactory(String type) {
        return adMap.get(type);
    }
}
