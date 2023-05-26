package octo.steeve.authenticator.controllers;

import octo.steeve.authenticator.usecases.AuthenticateUser;
import octo.steeve.authenticator.usecases.AuthenticateUserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/auth")
    public AuthResponseBody authenticate() {
        var authenticateUser = new AuthenticateUser();

        var request = new AuthenticateUserRequest("dertex", "killer");
        var token = authenticateUser.execute(request).token();

        return new AuthResponseBody(token);
    }
}
