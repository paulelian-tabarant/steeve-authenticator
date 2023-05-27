package octo.steeve.authenticator.usecases;

import java.util.UUID;

public class AuthenticateUser {
    public AuthenticateUserResult execute(AuthenticateUserRequest request) throws NameMissingException, PasswordMissingException {
        if (request.name() == null) throw new NameMissingException();
        if (request.password() == null) throw new PasswordMissingException();

        return new AuthenticateUserResult(
            UUID.randomUUID().toString().substring(0, 32)
        );
    }
}
