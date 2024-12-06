package project.marketplace.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.mindrot.jbcrypt.BCrypt;

/**
 * The basic user model. It is annotated with @Entity as it is the User class that 
 * will be used as a DTO (data-transfer object). It uses Bcrypt to encrypt the user's password
 */
@Entity
@Table(name = "users")
public class User {
    private static final int WFACTOR = 12;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String passwordHash;

    @NotNull
    private boolean validated;

    /**
     * Creates new user object with no validation
     */
    public User() {
        this.validated = false;
    }

    /**
     * Returns the id of the user
     *
     * @return the id of the user
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Returns the first name of the user
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the last name of the user
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns the email of the user
     *
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the hash of the user's password
     *
     * @return the hash of the user's password
     */
    public String getPasswordHash() {
        return this.passwordHash;
    }

    /**
     * Gets the validation status of the user.
     *
     * @return the validation status of the user
     */
    public boolean getValidation() {
        return this.validated;
    }

    /**
     * Sets the user's id.
     *
     * @param id The id to be set for the user.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the user's first name.
     *
     * @param fname The first name to set for the user.
     */
    public void setFirstName(String fname) {
        this.firstName = fname;
    }

    /**
     * Sets the user's last name.
     *
     * @param lname The last name to set for the user.
     */
    public void setLastName(String lname) {
        this.lastName = lname;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The email address to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's password hash.
     * The password is encrypted before it is stored.
     *
     * @param password The plaintext password to be encrypted and stored.
     */
    public void setPasswordHash(String password) {
        this.passwordHash = encryptPassword(password);
    }

    /**
     * Sets the user's password hash.
     *
     * @param password The plaintext password to be stored.
     */
    public void setPassword(String password) {
        this.passwordHash = password;
    }

    /**
     * Sets the validation status of the user.
     *
     * @param bool The validation status to set. True if the user is validated, false otherwise.
     */
    public void setValidated(boolean bool) {
        this.validated = bool;
    }

    /**
     * Hashes a given string and returns it
     * 
     * @param password the password to hash - must not be null 
     */
    public static String encryptPassword(String password) {
        int wFactor = WFACTOR;
        return BCrypt.hashpw(password, BCrypt.gensalt(wFactor));
    }

    /**
     * Compares the given password with this password. Returns true is if passwords match, false otherwise
     * 
     * @returns true if passwords match, false otherwise
     */
    public Boolean compare(String password) {
        return BCrypt.checkpw(password, this.getPasswordHash());
    }

    /**
     * Overrides the equals method using the email field
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    /**
     * Overrides the hashcode method using the hashcode of the email field
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
