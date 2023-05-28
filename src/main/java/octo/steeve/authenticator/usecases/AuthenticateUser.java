package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticateUser {
    private final UserRepository userRepository;

    public AuthenticateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticateUserResult execute(AuthenticateUserParams request) throws NameMissingException, PasswordMissingException, UserDoesNotExistException {
        var user = request.user();

        if (user.name() == null) throw new NameMissingException();
        if (user.password() == null) throw new PasswordMissingException();

        userRepository.findBy(user).orElseThrow(UserDoesNotExistException::new);

        return new AuthenticateUserResult(UUID.randomUUID().toString().substring(0, 32));
    }
}
