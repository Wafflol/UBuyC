package project.marketplace.daos;

/**
 * Creates a UserAlreadyExistsException checked exception
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String error) {
        super(error);
    }
}
