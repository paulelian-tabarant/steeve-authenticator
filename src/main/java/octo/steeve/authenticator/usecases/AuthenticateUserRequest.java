package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.User;

public record AuthenticateUserRequest(User user) {
}
