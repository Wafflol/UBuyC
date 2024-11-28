package project.marketplace.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import project.marketplace.models.Listing;

/**
 * Creates a ListingDao object to access data from the database's listing table. Uses NamedParameterJdbcTemplates
 * to stop SQL injection attacks.
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ListingDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Adds a new listing into the listing table of the database.
     * 
     * @param listing the listing to be added
     */
    public void createListing(Listing listing) {
        String sql = "INSERT INTO listings (email, title, description, price, imagePath, listingAge) VALUES(:email, :title, :description, :price, :imagePath, :listingAge)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("email", listing.getEmail())
            .addValue("title", listing.getTitle())
            .addValue("description", listing.getDescription())
            .addValue("price", listing.getPrice())
            .addValue("imagePath", /*listing.getImagePath()*/"/") // TODO: figure out how to store image path and image
            .addValue("listingAge", listing.getListingAge());
        System.out.println("createListing: email = " + listing.getEmail());
        System.out.println("createListing: title = " + listing.getTitle());
        System.out.println("createListing: description = " + listing.getDescription());
        System.out.println("createListing: price = " + listing.getPrice());
        System.out.println("createListing: imagePath = " + listing.getImagePath());
        System.out.println("createListing: listingAge = " + listing.getListingAge());
        jdbcTemplate.update(sql, parameters);
    }
}
