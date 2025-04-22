package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra;

import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.gateway.UserRepositoryGateway;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryAdapter implements UserRepositoryGateway {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public Mono<SignupUserRecord> save(SignupUserRecord user) {
        var newUser = new User(user.username(), user.email(), user.password());
        users.put(newUser.username().value(), newUser);
        return Mono.just(user);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return Mono.just(users.containsKey(username));
    }

    @Override
    public Mono<SigninUserRecord> findByUsername(String username) {
        var foundUser = users.get(username);
        return Mono.justOrEmpty(new SigninUserRecord(foundUser.username(), foundUser.password()));
    }

}
