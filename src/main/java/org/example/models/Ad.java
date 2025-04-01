package org.example.models;

/**
 * The {@code Ad} class is an abstract representation of an advertisement.
 * It contains common properties such as description, location, and price,
 * and requires subclasses to define their specific type.
 */
public abstract class Ad {
    private String description;
    private String location;
    private int price;

    /**
     * Constructs an {@code Ad} with the specified description, location, and price.
     *
     * @param description A brief description of the advertisement.
     * @param location The location associated with the advertisement.
     * @param price The price of the advertisement.
     */
    public Ad(String description, String location, int price) {
        this.description = description;
        this.location = location;
        this.price = price;
    }

    /**
     * Gets the type of the advertisement.
     * Subclasses must implement this method to specify the type.
     *
     * @return The type of the advertisement, as a string.
     */
    public abstract String getType();

    /**
     * Returns a string representation of the advertisement, including its type,
     * description, location, and price.
     *
     * @return A formatted string representing the advertisement.
     */
    @Override
    public String toString() {
        return "type: " + getType() + ", description: " + this.description + ", location: " + this.location + ", price: " + this.price;
    }
}