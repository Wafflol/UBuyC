package project.marketplace.models;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ReducedListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String title;
    private String description;
    private double price;
    private MultipartFile image;
    private LocalDateTime listingAge;
    private String base64Image;
    private String imageType;

    public ReducedListing() {
        // initialize empty listing
    }

    // Creates new Reduced Listing object meant for display from a Listing object 
    // by encoding the byte array represetation of the image
    public ReducedListing(Listing listing) {
        this.id = listing.getId();
        this.email = listing.getEmail();
        this.title = listing.getTitle();
        this.description = listing.getDescription();
        this.price = listing.getPrice();
        this.listingAge = listing.getListingAge();
        this.base64Image = Base64.getEncoder().encodeToString(listing.getImage());
        this.imageType = listing.getImageType();
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
    public MultipartFile getImage() {
        return this.image;
    }

    /**
     * Returns the age of the listing
     * @return the age of the listing
     */
    public LocalDateTime getListingAge() {
        return this.listingAge;
    }

    /**
     * Returns the base64 encoded version of the image
     * @return the base64 encoded version of the image
     */
    public String getBase64Image() {
        return this.base64Image;
    }

    public String getImageType() {
        return this.imageType;
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
    public void setImage(MultipartFile image) {
        this.image = image;
    }

    /**
     * Sets the listing age to present datetime
     * @param listingAge the current datetime
     */
    public void setListingAge(LocalDateTime listingAge) {
        this.listingAge = listingAge;
    }

    /**
     * Returns the base64 encoded version of the image
     * @return the base64 encoded version of the image
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * Sets the id of the listing
     * @param id id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the unique id of the listing
     * @return id of listing
     */
    public long getId() {
        return this.id;
    }

    /**
     * Overrides equal method for this ReducedListing object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReducedListing listing = (ReducedListing) o;
        return id == listing.id;
    }

    /**
     * Overrides the hashcode method for this ReducedListing object
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

