package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra;

import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public Mono<User> save(User user) {
        users.put(user.username().value(), user);
        return Mono.just(user);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return Mono.defer(() -> Mono.just(users.containsKey(username)));
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return Mono.justOrEmpty(users.get(username));
    }

}
