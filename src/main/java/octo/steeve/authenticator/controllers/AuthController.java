package octo.steeve.authenticator.controllers;

import octo.steeve.authenticator.entities.User;
import octo.steeve.authenticator.usecases.*;
import octo.steeve.authenticator.usecases.exceptions.NameMissingException;
import octo.steeve.authenticator.usecases.exceptions.PasswordMissingException;
import octo.steeve.authenticator.usecases.exceptions.UserDoesNotExistException;
import octo.steeve.authenticator.usecases.ports.AuthenticateUserParams;
import octo.steeve.authenticator.usecases.ports.AuthenticateUserResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AuthController {
    private final AuthenticateUser authenticateUser;

    public AuthController(AuthenticateUser authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

    @PostMapping(value = "/auth")
    public AuthResponse authenticate(@RequestBody AuthRequest request) throws NameMissingException {
        var params = toAuthenticateUserParams(request);

        try {
            var authenticateUserResult = authenticateUser.execute(params);
            return toAuthResponse(authenticateUserResult);
        } catch (NameMissingException | PasswordMissingException | UserDoesNotExistException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
        }
    }

    private static AuthResponse toAuthResponse(AuthenticateUserResult result) {
        return new AuthResponse(result.token());
    }

    private static AuthenticateUserParams toAuthenticateUserParams(AuthRequest requestBody) {
        var user = new User(requestBody.name(), requestBody.password());
        return new AuthenticateUserParams(user);
    }
}
