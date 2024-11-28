package project.marketplace.controller;

/**
 * Creates an InvalidEmailException
 */
public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}