package octo.steeve.authenticator.usecases;

import java.util.UUID;

public class AuthenticateUser {
    public AuthenticateUserResult execute(AuthenticateUserRequest request) {

        return new AuthenticateUserResult(
            UUID.randomUUID().toString().substring(0, 32)
        );
    }
}
