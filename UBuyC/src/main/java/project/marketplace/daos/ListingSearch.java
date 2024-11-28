package project.marketplace.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import project.marketplace.models.Listing;

    
/**
 * Creates an ListingSearch object to access data from the listings database.
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ListingSearch {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void ensureConnectionSecure() throws IllegalStateException {
        try {
            jdbcTemplate.queryForList("SELECT email FROM users where fname = 'peter'", new MapSqlParameterSource());
        } catch (DataAccessException e) {
            throw new IllegalStateException("Database connection is not secure or unavailable", e);
        }
    }

    public List<String> searchListings(String query) {
        ensureConnectionSecure();
        String sql = """
                     SELECT title
                     FROM listings
                     WHERE document_with_idx @@ to_tsquery(:query)
                     ORDER BY ts_rank(document_with_weights, plainto_tsquery(:query)) DESC;
                     """;

        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("query", query);
        List<String> list = jdbcTemplate.queryForList(sql, parameters, String.class);

        return list;
    }
}
