package org.example;

public class Ad {
    private String description;
    private String location;
    private int price;

    public Ad(String description, String location, int price) {
        this.description = description;
        this.location = location;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{description: " + this.description + ", location: " + this.location + ", price: " + this.price + "}";
    }
}