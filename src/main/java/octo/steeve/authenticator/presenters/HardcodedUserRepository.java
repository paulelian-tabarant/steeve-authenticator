package octo.steeve.authenticator.presenters;

import octo.steeve.authenticator.entities.User;
import octo.steeve.authenticator.entities.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HardcodedUserRepository implements UserRepository {
    List<User> users = List.of(new User("dertex", "killer"));

    @Override
    public Optional<User> findBy(User user) {
        return users.stream()
            .filter(currentUser -> currentUser.equals(user))
            .findFirst();
    }
}
