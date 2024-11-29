package project.marketplace.daos;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import project.marketplace.models.Listing;
    
/**
 * Provides methods to search for listings in the marketplace database.
 * This class interacts with the database using JDBC to fetch listing data
 * based on search queries.
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ListingSearch {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Ensures that the database connection is secure and available.
     * This method performs a simple query to verify connectivity.
     * 
     * @throws IllegalStateException if the database connection is not secure or unavailable.
     */
    public void ensureConnectionSecure() throws IllegalStateException {
        try {
            jdbcTemplate.queryForList("SELECT email FROM users where fname = 'peter'", new MapSqlParameterSource());
        } catch (DataAccessException e) {
            throw new IllegalStateException("Database connection is not secure or unavailable", e);
        }
    }

    /**
     * Searches the database for listings that match the given query.
     * The search utilizes full-text search with ranking based on relevance.
     * 
     * @param query the search query string provided by the user.
     * @return a list of {@link Listing} objects that match the search criteria.
     */
    public List<Listing> searchListings(String query) {
        ensureConnectionSecure();

        if (query == null || query.trim().isEmpty()) {
            return this.getAll();
        }

        String sql = """
                     SELECT id, email, title, description, price, imagepath, listingage 
                     FROM listings
                     WHERE document_with_idx @@ to_tsquery(:query)
                     ORDER BY ts_rank(document_with_weights, plainto_tsquery(:query)) DESC;
                     """;

        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("query", query + ":*");

        return jdbcTemplate.query(sql, parameters, listingRowMapper());
    }

    public Listing getListingById(long id) {
        ensureConnectionSecure();
        String sql = """
                     SELECT id, email, title, description, price, imagepath, listingage 
                     FROM listings
                     WHERE id = :id
                     """;
    
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
    
        // Use queryForObject to get a single result
        return jdbcTemplate.queryForObject(sql, parameters, listingRowMapper());
    }

    public List<Listing> getAll() {
        ensureConnectionSecure();
        String sql = """
                     SELECT id, email, title, description, price, imagepath, listingage 
                     FROM listings
                     """;
    
        List<Listing> listings = jdbcTemplate.query(sql, listingRowMapper());
        return listings.isEmpty() ? Collections.emptyList() : listings;
    }

    /**
     * Maps each row of the result set to a {@link Listing} object.
     * 
     * @return a {@link RowMapper} that converts rows from the database into {@link Listing} objects.
     */
    private RowMapper<Listing> listingRowMapper() {
        return (rs, rowNum) -> new Listing(
            rs.getLong("id"),
            rs.getString("email") != null ? rs.getString("email") : "",   // Default to empty string if null
            rs.getString("title") != null ? rs.getString("title") : "",   // Default to empty string if null
            rs.getString("description") != null ? rs.getString("description") : "", // Default to empty string if null
            rs.getDouble("price") != 0.0 ? rs.getDouble("price") : 0.0,    // Default to 0.0 if null (or you can use `Double` for nullable)
            rs.getString("imagepath") != null ? rs.getString("imagepath") : "", // Default to empty string if null
            rs.getTimestamp("listingage") != null ? rs.getTimestamp("listingage").toLocalDateTime() : LocalDateTime.now() // Default to current time if null
        );
    }
}
