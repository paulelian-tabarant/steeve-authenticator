package octo.steeve.authenticator.usecases;

import octo.steeve.authenticator.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findBy(User user);
}
