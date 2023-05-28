package octo.steeve.authenticator.entities;

import octo.steeve.authenticator.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findBy(User user);
}
