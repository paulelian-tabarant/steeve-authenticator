package octo.steeve.authenticator.usecases.exceptions;

public class NameMissingException extends Exception {
    public NameMissingException() {
        super("Name is missing");
    }
}
