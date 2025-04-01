package org.example.models;

import org.example.models.Ad;

/**
 * The {@code CarAd} class represents an advertisement for a car.
 * It extends the {@code Ad} class and includes additional details specific to cars, such as the brand.
 */
public class CarAd extends Ad {
    private String brand;

    /**
     * Constructs a {@code CarAd} with the specified details.
     *
     * @param description A brief description of the car.
     * @param location The location where the car is available.
     * @param price The price of the car.
     * @param brand The brand of the car.
     */
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

    /**
     * Returns a string representation of the car advertisement, including its brand.
     *
     * @return A formatted string representing the car advertisement.
     */
    @Override
    public String toString() {
        return super.toString() + ", brand: " + this.brand;
    }
}
