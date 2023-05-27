package octo.steeve.authenticator.controllers;

import octo.steeve.authenticator.usecases.AuthenticateUser;
import octo.steeve.authenticator.usecases.AuthenticateUserRequest;
import octo.steeve.authenticator.usecases.NameMissingException;
import octo.steeve.authenticator.usecases.PasswordMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AuthController {

    @PostMapping(value = "/auth")
    public AuthResponseBody authenticate(@RequestBody AuthRequestBody requestBody) throws NameMissingException {
        var authenticateUser = new AuthenticateUser();
        var request = new AuthenticateUserRequest(requestBody.name(), requestBody.password());

        try {
            var token = authenticateUser.execute(request).token();
            return new AuthResponseBody(token);

        } catch (NameMissingException | PasswordMissingException nameMissingException) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Name is missing", nameMissingException);
        }
    }
}
