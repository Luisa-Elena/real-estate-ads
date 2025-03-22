package org.example;

public class CarAd extends Ad {
    private String brand;

    public CarAd(String description, String location, int price, String brand) {
        super(description, location, price);
        this.brand = brand;
    }

    @Override
    public String getType() {
        return "CAR";
    }

    @Override
    public String toString() {
        return super.toString() + ", brand: " + this.brand;
    }
}
