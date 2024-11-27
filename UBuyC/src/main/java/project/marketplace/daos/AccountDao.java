package project.marketplace.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import project.marketplace.models.User;
import project.marketplace.daos.UserAlreadyExistsException;

@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AccountDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private Boolean emailExists(String email) {
        List<Boolean> namesFound = jdbcTemplate.queryForList("SELECT EXISTS (SELECT 1 FROM users WHERE email = '" + email + "')", new MapSqlParameterSource(), Boolean.class); 
        System.out.println(namesFound.get(0));
        return namesFound.get(0);
    }

    public int createUser(User user) throws UserAlreadyExistsException {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistsException("An account has already been registered with that email address.");
        }
        String sql = "INSERT INTO users (fname, lname, email, password) VALUE(:fname, :lname, :email, :password)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("fname", user.getFirstName())
            .addValue("fname", user.getLastName())
            .addValue("fname", user.getEmail())
            .addValue("fname", user.getPasswordHash());
        return jdbcTemplate.update(sql, parameters);
    }
}
