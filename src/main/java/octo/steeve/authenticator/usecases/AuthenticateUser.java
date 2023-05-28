package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.Token;
import octo.steeve.authenticator.entities.TokenGenerator;
import octo.steeve.authenticator.entities.UserRepository;
import octo.steeve.authenticator.usecases.exceptions.NameMissingException;
import octo.steeve.authenticator.usecases.exceptions.PasswordMissingException;
import octo.steeve.authenticator.usecases.exceptions.UserDoesNotExistException;
import octo.steeve.authenticator.usecases.ports.AuthenticateUserParams;
import octo.steeve.authenticator.usecases.ports.AuthenticateUserResult;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUser {
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public AuthenticateUser(UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public AuthenticateUserResult execute(AuthenticateUserParams params) throws NameMissingException, PasswordMissingException, UserDoesNotExistException {
        var user = params.user();

        if (user.name() == null) throw new NameMissingException();
        if (user.password() == null) throw new PasswordMissingException();

        userRepository.findBy(user).orElseThrow(UserDoesNotExistException::new);

        var token = tokenGenerator.generateToken();

        return toResult(token);
    }

    private static AuthenticateUserResult toResult(Token token) {
        return new AuthenticateUserResult(token.value());
    }
}
