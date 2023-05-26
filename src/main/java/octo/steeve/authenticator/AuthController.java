package octo.steeve.authenticator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class AuthController {

    @PostMapping("/auth")
    public void authenticate() {

    }
}
