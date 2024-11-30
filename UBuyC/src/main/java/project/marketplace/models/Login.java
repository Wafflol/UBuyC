package project.marketplace.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Login {

    /**
     * email of login instance, cannot be blank or null
     */
    @NotBlank
    @NotNull
    @Email
    private String email;

    /**
     * password of login instance, cannot be blank or null
     */
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
