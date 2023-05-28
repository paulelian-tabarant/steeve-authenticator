package octo.steeve.authenticator.usecases;

public class NameMissingException extends Exception {
    public NameMissingException() {
        super("Name is missing");
    }
}
