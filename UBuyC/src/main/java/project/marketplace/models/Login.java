package project.marketplace.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Login {

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String password;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = encryptPassword(password);
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
}
