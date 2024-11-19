package project.marketplace.models;

import java.util.Set;

public class Listing {
    private User owner;
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private Set<String> tags;

    public Listing (User owner, String title, String description, double price, String imagePath, Set<String> tags) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.tags = tags;
    }
}
