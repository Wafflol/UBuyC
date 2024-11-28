package project.marketplace.models;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;

import java.util.HashSet;
import java.time.LocalDateTime;

@Entity
public class Listing {
    private final User owner;
    private String title;
    private String description;
    private double price;
    private String imagePath;
    private Set<String> tags;
    private final LocalDateTime listingAge;
    private final int listingId;

    /**
     * creates a new listing object
     * @param owner the owner of the listing
     * @param title the title of the listing
     * @param description the description of the listing
     * @param price the price of the listing
     * @param imagePath the path to the image of the listing
     * @param tags the tags of the listing
     * @param listingId the listing ID
     */
    public Listing (User owner, String title, String description, double price, String imagePath, Set<String> tags, int listingId) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.tags = tags;
        this.listingAge = java.time.LocalDateTime.now();
        this.listingId = listingId;
    }

    /**
     * Returns the owner of the listing
     * @return the owner of the listing
     */
    public User getUser() {
        return this.owner;
    }

    /**
     * Returns the title of the listing
     * @return the title of the listing
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the description of the listing
     * @return the description of the listing
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the price of the listing
     * @return the price of the listing
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the image path of the listing image
     * @return the image path of the listing image
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Returns the tags of the listing
     * @return the tags of the listing
     */
    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    /**
     * Returns the age of the listing
     * @return the age of the listing
     */
    public LocalDateTime getListingAge() {
        return this.listingAge;
    }

    /**
     * Returns the age of the listing
     * @return the age of the listing
     */
    public int getListingId() {
        return this.listingId;
    }

    /**
     * Sets the title of the listing
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the description of the listing
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the price of the listing
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the image path of the listing image
     * @param imagePath the image path to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Sets the tags of the listing
     * @param tags the tags to set
     */
    public void setTags(Set<String> tags) {
        this.tags = new HashSet<>(tags);
    }

    /**
     * Adds a set of tags to the current tags
     * @param tags the tags to add
     */
    public void addTags(Set<String> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Removes a set of tags from the current tags
     * @param tags the tags to remove
     */
    public void removeTags(Set<String> tags) {
        this.tags.removeAll(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return listingId == listing.listingId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(listingId);
    }
}

