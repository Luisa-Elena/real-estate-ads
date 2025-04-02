package org.example.models;

import org.example.models.Ad;

/**
 * The {@code RealEstateAd} class represents an advertisement for real estate properties.
 * It extends the {@code Ad} class and includes additional details specific to real estate, such as surface area.
 */
public class RealEstateAd extends Ad {
    private final double surface;

    public RealEstateAd(String description, String location, int price, double surface) {
        super(description, location, price);
        this.surface = surface;
    }

    /**
     * Returns the type of advertisement, which is always "REAL-ESTATE" for this class.
     *
     * @return The string "REAL-ESTATE".
     */
    @Override
    public String getType() {
        return "REAL-ESTATE";
    }

    @Override
    public String toString() {
        return super.toString() + ", surface: " + this.surface;
    }
}
