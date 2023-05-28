package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthenticateUser {

    private final HardcodedUserRepository userRepository;

    public AuthenticateUser(HardcodedUserRepository userRepository) {
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
