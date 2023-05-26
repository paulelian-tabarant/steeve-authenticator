package octo.steeve.authenticator.controllers;

public record AuthRequestBody(
    String name,
    String password) {
}
