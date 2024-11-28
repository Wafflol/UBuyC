package project.marketplace.daos;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import project.marketplace.models.Listing;

import org.springframework.jdbc.core.RowMapper;

import project.marketplace.models.User;
    
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
        String sql = """
                     SELECT id, email, title, description, price, imagepath, tags, listingage 
                     FROM listings
                     WHERE document_with_idx @@ to_tsquery(:query)
                     ORDER BY ts_rank(document_with_weights, plainto_tsquery(:query)) DESC;
                     """;

        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("query", query);

        return jdbcTemplate.query(sql, parameters, listingRowMapper());
    }

    /**
     * Maps each row of the result set to a {@link Listing} object.
     * 
     * @return a {@link RowMapper} that converts rows from the database into {@link Listing} objects.
     */
    private RowMapper<Listing> listingRowMapper() {
        return (rs, rowNum) -> new Listing(
            new User(rs.getString("email")),
            rs.getString("title"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getString("imagepath"),
            Set.of(rs.getString("tags").split(",")),
            rs.getInt("id")
        );
    }
}
