package octo.steeve.authenticator.usecases.exceptions;

public class PasswordMissingException extends Exception {
    public PasswordMissingException() {
        super("Password is missing");
    }
}
