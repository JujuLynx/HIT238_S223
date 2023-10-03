package com.example.tooltopia;

public class Items {
    private int id;             // Unique ID for the item
    private String name;       // Name of the item
    private String description; // Description of the item
    private double price;      // Price of the item
    private String imageUrl;   // URL for the image of the item

    // Constructor
    public Items(int id, String name, String description, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl; // Set the imageUrl
    }

    // Getters and Setters for the attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // New getter and setter for imageUrl
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return name + " - " + description + " - $" + price + " - Image: " + imageUrl;
    }
}






