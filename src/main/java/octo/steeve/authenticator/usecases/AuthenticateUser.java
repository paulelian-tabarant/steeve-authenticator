package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.User;

import java.util.List;
import java.util.UUID;

public class AuthenticateUser {
    List<User> users = List.of(new User("dertex", "killer"));

    public AuthenticateUserResult execute(AuthenticateUserRequest request) throws NameMissingException, PasswordMissingException, UserDoesNotExistException {
        var user = request.user();

        if (user.name() == null) throw new NameMissingException();
        if (user.password() == null) throw new PasswordMissingException();

        var userExists = users.contains(user);

        if (!userExists) throw new UserDoesNotExistException();

        return new AuthenticateUserResult(
            UUID.randomUUID().toString().substring(0, 32)
        );
    }
}
