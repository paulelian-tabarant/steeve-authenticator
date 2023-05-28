package octo.steeve.authenticator.controllers;

import octo.steeve.authenticator.entities.User;
import octo.steeve.authenticator.usecases.*;
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
        var user = new User(requestBody.name(), requestBody.password());
        var request = new AuthenticateUserRequest(user);

        try {
            var token = authenticateUser.execute(request).token();
            return new AuthResponseBody(token);

        } catch (NameMissingException nameMissingException) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Name is missing", nameMissingException);
        } catch (PasswordMissingException passwordMissingException) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is missing", passwordMissingException);
        } catch (UserDoesNotExistException userDoesNotExistException) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not exist", userDoesNotExistException);
        }
    }
}
