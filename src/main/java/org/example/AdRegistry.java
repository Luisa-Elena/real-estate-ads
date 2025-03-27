package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code AdRegistry} class is a singleton that manages different types of advertisement factories.
 * It provides access to the appropriate factory based on the advertisement type.
 */
public class AdRegistry {
    private static AdRegistry instance = null;
    private final Map<String, AdFactory> adMap = new HashMap<>();

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the registry with available advertisement factories.
     */
    private AdRegistry() {
        initializeAds();
    }

    /**
     * Returns the singleton instance of the {@code AdRegistry}.
     * Ensures thread safety using synchronized access.
     *
     * @return The singleton instance of {@code AdRegistry}.
     */
    public static synchronized AdRegistry getInstance() {
        if (instance == null) {
            instance = new AdRegistry();
        }
        return instance;
    }

    /**
     * Initializes the registry with predefined advertisement factories.
     */
    private void initializeAds() {
        adMap.put("REAL-ESTATE", new RealEstateAdFactory());
        adMap.put("CAR", new CarAdFactory());
    }

    /**
     * Retrieves the {@code AdFactory} corresponding to the specified advertisement type.
     *
     * @param type The type of advertisement (e.g., "REAL-ESTATE", "CAR").
     * @return The corresponding {@code AdFactory}, or {@code null} if no factory exists for the type.
     */
    public AdFactory getAdFactory(String type) {
        return adMap.get(type);
    }
}
