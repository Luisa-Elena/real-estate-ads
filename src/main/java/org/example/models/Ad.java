package org.example.models;

/**
 * The {@code Ad} class is an abstract representation of an advertisement.
 * It contains common properties such as description, location, and price,
 * and requires subclasses to define their specific type.
 */
public abstract class Ad {
    private final String description;
    private final String location;
    private final int price;

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