package octo.steeve.authenticator.usecases;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException() {
        super("User does not exist");
    }
}
