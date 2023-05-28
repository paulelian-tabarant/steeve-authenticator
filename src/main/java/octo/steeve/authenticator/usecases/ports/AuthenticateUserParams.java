package octo.steeve.authenticator.usecases.ports;

import octo.steeve.authenticator.entities.User;

public record AuthenticateUserParams(User user) {
}
