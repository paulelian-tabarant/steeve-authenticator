package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HardcodedUserRepository {
    List<User> users = List.of(new User("dertex", "killer"));

    public Optional<User> findBy(User user) {
        return users.stream()
            .filter(currentUser -> currentUser.equals(user))
            .findFirst();
    }
}
