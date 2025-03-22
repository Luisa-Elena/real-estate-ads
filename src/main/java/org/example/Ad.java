package org.example;

public abstract class Ad {
    private String description;
    private String location;
    private int price;

    public Ad(String description, String location, int price) {
        this.description = description;
        this.location = location;
        this.price = price;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "type: " + getType() + ", description: " + this.description + ", location: " + this.location + ", price: " + this.price;
    }
}