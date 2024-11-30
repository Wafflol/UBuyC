package project.marketplace.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * The User information class with reduced information meant to be binded to the login form.
 * It contains just the email and password of a User.
 */
public class Login {

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String password;

    /**
     * Gets the email of this login instance
     * @return email of the user logging in
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the password (encrypted) of this login instance
     * @return password of user logging in
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the email of this login instance 
     * @param email email of user logging in
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password (encrypted) of this login instance
     * @param password passowrd of user logging in
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
