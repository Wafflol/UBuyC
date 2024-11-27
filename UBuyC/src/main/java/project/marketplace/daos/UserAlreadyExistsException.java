package project.marketplace.daos;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String error) {
        super(error);
    }
}
