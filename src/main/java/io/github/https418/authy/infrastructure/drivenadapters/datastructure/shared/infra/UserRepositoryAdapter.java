package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra;

import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.UserData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final Map<String, UserData> users = new ConcurrentHashMap<>();

    @Override
    public Mono<UserData> save(UserData userData) {
        users.put(userData.username().value(), userData);
        return Mono.just(userData);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return Mono.defer(() -> Mono.just(users.containsKey(username)));
    }

    @Override
    public Mono<UserData> findByUsername(String username) {
        return Mono.justOrEmpty(users.get(username));
    }

}
