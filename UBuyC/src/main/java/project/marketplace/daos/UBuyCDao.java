package project.marketplace.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="project.marketplace.daos.UBuyCDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class UBuyCDao {

    private static final String GET_EMAIL = "SELECT email FROM users WHERE name = 'Alice'";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getEmail(){
        List<String> namesFound = jdbcTemplate.queryForList(GET_EMAIL, new MapSqlParameterSource(), String.class); 
        System.out.println(namesFound.get(0));
        return namesFound.get(0);
    }
}
