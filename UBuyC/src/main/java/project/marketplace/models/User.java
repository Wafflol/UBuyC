package project.marketplace.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

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
     * @return the id of the user
     */
    public Long getId() {
        return this.id;
    }

    /**
      * Returns the first name of the user
      * @return the first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the last name of the user
     * @return the last name of the user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns the email of the user
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the hash of the user's password
     * @return the has of the user's password
     */
    public String getPasswordHash() {
        return this.passwordHash;
    }

    public boolean getValidation() {
        return this.validated;
    }

    public void setFirstName(String fname) {
        this.firstName = fname;
    }

    public void setLastName(String lname) {
        this.lastName = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = encryptPassword(password);
    }

    public void setValidated(boolean bool) {
        this.validated = bool;
    }
    
    /**
     * Hashes a given string and returns it
     * @param password the to hash password
     * @precondition - password not null
     * @postcondition - sets passwordHash
     */
    public String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8)); 
            return bytesToHex(encodedhash);
        }
        catch (Exception e) {
            System.out.println("Wrong algorithm name");
            return null;
        }
    }
    
    /**
     * Turns a list of bytes into its hex representation
     * @param hash the list of bytes to transform
     * @return the hex representation
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Overrides the equals method using the email field
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
