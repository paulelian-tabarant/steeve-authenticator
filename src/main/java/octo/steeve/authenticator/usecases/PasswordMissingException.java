package octo.steeve.authenticator.usecases;

public class PasswordMissingException extends Exception {
    public PasswordMissingException() {
        super("Password is missing");
    }
}
