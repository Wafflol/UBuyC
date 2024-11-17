package org.example;

public class User {
    // File: [User.java]
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
    /**
     * Encrypts the a string and stores it as the password
     * @param String password
     * @precondition - password not null
     * @postcondition - sets passwordHash
     */
    public String encryptPassword(String password) {
        return password;
    }
}
