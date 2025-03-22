package org.example;

public class RealEstateAd extends Ad {
    private double surface;
    public RealEstateAd(String description, String location, int price, double surface) {
        super(description, location, price);
        this.surface = surface;
    }

    @Override
    public String getType() {
        return "REAL-ESTATE";
    }

    @Override
    public String toString() {
        return super.toString() + ", surface: " + this.surface;
    }
}
