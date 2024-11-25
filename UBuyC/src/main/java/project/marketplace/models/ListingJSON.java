package project.marketplace.models;

import java.util.Set;

/**
 * This record is used to store listing
 * information when a user creates a new listing
 * @param owner the username/email of the owner
 * @param title the title of the listing
 * @param description the description of the listing
 * @param price the price of the listing
 * @param imagePath the image path
 * @param tags the tags of the listing
 */
public record ListingJSON (String owner, String title, String description,
                           double price, String imagePath, Set<String> tags){}