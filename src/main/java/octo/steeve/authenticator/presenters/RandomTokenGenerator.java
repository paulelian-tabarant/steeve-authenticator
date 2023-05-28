package octo.steeve.authenticator.presenters;

import octo.steeve.authenticator.entities.Token;
import octo.steeve.authenticator.entities.TokenGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomTokenGenerator implements TokenGenerator {
    @Override
    public Token generateToken() {
        return new Token(UUID.randomUUID().toString().substring(0, 32));
    }
}
