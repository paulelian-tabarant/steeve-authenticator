package octo.steeve.authenticator.usecases.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("User does not exist");
    }
}
