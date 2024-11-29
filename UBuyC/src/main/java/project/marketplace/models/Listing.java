package project.marketplace.models;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String title;
    private String description;
    private double price;
    private byte[] image;
    private String imageType;
    private LocalDateTime listingAge;

    public Listing() {
        //lmao
    }

    /**
     * creates a new listing object
     * @param owner the owner of the listing
     * @param title the title of the listing
     * @param description the description of the listing
     * @param price the price of the listing
     * @param image the path to the image of the listing
     */
    public Listing (String email, String title, String description, double price, byte[] image) {
        this.email = email;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.listingAge = java.time.LocalDateTime.now();
    }

    /**
     * creates a new listing object
     * @param owner the owner of the listing
     * @param title the title of the listing
     * @param description the description of the listing
     * @param price the price of the listing
     * @param image the path to the image of the listing
     * @param tags the tags of the listing
     */
    public Listing (long id, String email, String title, String description, double price, byte[] image, String imageType, LocalDateTime listingAge) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
        this.imageType = imageType;
        this.listingAge = listingAge;
    }

    // For creating a new listing from a reducedListing object
    public Listing (ReducedListing reducedListing) {
        this.title = reducedListing.getTitle();
        this.description = reducedListing.getDescription();
        this.price = reducedListing.getPrice();
        try {
            this.image = reducedListing.getImage().getBytes();
        } catch (Exception e) {
            System.out.println("Failed to do get image bytes in Listing contructor");
        }
        this.imageType = reducedListing.getImage().getContentType();
        this.listingAge = LocalDateTime.now();
    }

    /**
     * Returns the owner of the listing
     * @return the owner of the listing
     */
    public String getEmail() {
        return this.email;
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
    public byte[] getImage() {
        return this.image;
    }

    // TODO: add comemnts
    public String getImageType() {
        return this.imageType;
    }

    /**
     * Returns the age of the listing
     * @return the age of the listing
     */
    public LocalDateTime getListingAge() {
        return this.listingAge;
    }

    /**
     * Sets the email of the listing
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
    public void setImage(byte[] image) {
        this.image = image;
    }

    // TODO: add comments
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     * Sets the listing age to present datetime
     * @param listingAge the current datetime
     */
    public void setListingAge(LocalDateTime listingAge) {
        this.listingAge = listingAge;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return id == listing.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

