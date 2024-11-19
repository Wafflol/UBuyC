package org.example;

import java.security.MessageDigest;

import java.nio.charset.StandardCharsets;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    /**
     * Creates a new User object
     * @param String firstName
     * @param String lastName
     * @param String email
     * @param String passwordHash
     * @precondition firstName is not null, lastName is not null, email is not null, password is not null
     * @postcondition creates a user object and instantiates all instance variables
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = encryptPassword(password);
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    
    /**
     * Encrypts the a string and stores it as the password
     * @param String password
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
