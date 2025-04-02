package org.example.models;

import org.example.models.Ad;

/**
 * The {@code CarAd} class represents an advertisement for a car.
 * It extends the {@code Ad} class and includes additional details specific to cars, such as the brand.
 */
public class CarAd extends Ad {
    private final String brand;

    public CarAd(String description, String location, int price, String brand) {
        super(description, location, price);
        this.brand = brand;
    }

    /**
     * Returns the type of advertisement, which is always "CAR" for this class.
     *
     * @return The string "CAR".
     */
    @Override
    public String getType() {
        return "CAR";
    }

    @Override
    public String toString() {
        return super.toString() + ", brand: " + this.brand;
    }
}
