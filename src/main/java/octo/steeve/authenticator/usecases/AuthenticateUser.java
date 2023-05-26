package octo.steeve.authenticator.usecases;

import java.util.UUID;

public class AuthenticateUser {
    public AuthenticateUserResult execute(AuthenticateUserRequest request) throws NameMissingException {
        if (request.name() == null) throw new NameMissingException();

        return new AuthenticateUserResult(
            UUID.randomUUID().toString().substring(0, 32)
        );
    }
}
