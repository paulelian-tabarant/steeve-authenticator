package octo.steeve.authenticator.controllers;

public record AuthRequest(
    String name,
    String password) {
}
