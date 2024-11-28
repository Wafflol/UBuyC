package project.marketplace.daos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import project.marketplace.models.Login;
import project.marketplace.models.User;
import project.marketplace.models.VerificationToken;
    
/**
 * Creates an AccountDao object to access data from the accounts database. Uses NamedParameterJdbcTemplates
 * to stop SQL injection attacks.
 */
@Repository
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AccountDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Checks if the given email exists in the users table. Returns true if it exists, false otherwise.
     * 
     * @param email the given email
     * @return true if the email exists, false otherwise
     */
    private Boolean emailExists(String email) {
        List<Boolean> namesFound = jdbcTemplate.queryForList("SELECT EXISTS (SELECT 1 FROM users WHERE email = '" + email + "')", new MapSqlParameterSource(), Boolean.class);
        System.out.println(namesFound.get(0));
        return namesFound.get(0);
    }

    /**
     * Adds new user to accounts database if the user's email is not already registered
     *
     * @param userDTO - The user to be added
     * @return the user being added
     * @throws UserAlreadyExistsException - thrown if user's email is already registered
     */
    public User createUser(User userDTO) throws UserAlreadyExistsException {

       // checks if user already exists by email
       if (emailExists(userDTO.getEmail())) {
           throw new UserAlreadyExistsException("An account has already been registered with that email address.");
       }

       // defensive copying
       final User user = new User();
       user.setFirstName(userDTO.getFirstName());
       user.setLastName(userDTO.getLastName());
       user.setEmail(userDTO.getEmail());
       user.setPasswordHash(userDTO.getPasswordHash());

       // adding new user to database
       String sql = "INSERT INTO users (fname, lname, email, password) VALUES(:fname, :lname, :email, :password)";
       MapSqlParameterSource parameters = new MapSqlParameterSource()
           .addValue("fname", user.getFirstName())
           .addValue("lname", user.getLastName())
           .addValue("email", user.getEmail())
           .addValue("password", user.getPasswordHash());
       jdbcTemplate.update(sql, parameters);
       return user;
   }

    public int createVerificationToken(User user) {
        VerificationToken token = new VerificationToken(user);
        String sql = "INSERT INTO verification_tokens (email, otp, expiry_date) VALUES(:email, :otp, :expiry_date)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("email", token.getUser().getEmail())
            .addValue("otp", token.getOtp())
            .addValue("expiry_date", token.getExpiryDate());
        jdbcTemplate.update(sql, parameters);
        return token.getOtp();
    }

    /**
     * Returns the OTP with the matching email as the user
     * 
     * @param user The user that holds the token
     * @return a 6-digit OTP
     */
    public int getOtpByUser(User user) {
        String sql = "SELECT otp FROM verification_tokens WHERE email = '" + user.getEmail() + "'";
        List<Integer> otps = jdbcTemplate.queryForList(sql, new MapSqlParameterSource(), Integer.class);
        return otps.get(0);
    }

    /**
     * Returns the expiry date of the token held by the given user
     * 
     * @param user THe user that holds the token
     * @return the expiry date of the token
     */
    public LocalDate getTokenExpiryDateByUser(User user) {
        String sql = "SELECT expiry_date FROM verification_tokens WHERE email = '" + user.getEmail() + "'";
        List<LocalDate> expiryDate = jdbcTemplate.queryForList(sql, new MapSqlParameterSource(), LocalDate.class);
        return expiryDate.get(0);
    }

    /**
     * Updates the users table in the database to set the given user's validated field to true
     * 
     * @param user The user whose field is being updated
     */
    public void updateValidatedUser(User user) {
        String sql = "UPDATE users SET validated = true WHERE email = :email";
        MapSqlParameterSource parameters =  new MapSqlParameterSource();
        parameters.addValue("email", user.getEmail());
        jdbcTemplate.update(sql, parameters);
    }

    public boolean checkLoginInfo(Login login) {
        String sql = "SELECT password, validated FROM users WHERE email = :email";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("email", login.getEmail());

        try {
            return jdbcTemplate.queryForObject(sql, parameters, (rs, rowNum) -> {
                String storedPassword = rs.getString("password");
                boolean isValidated = rs.getBoolean("validated");
                return isValidated && storedPassword.equals(login.getPassword());
            });
        } catch (Exception e) {
            return false;
        }
    }
}
