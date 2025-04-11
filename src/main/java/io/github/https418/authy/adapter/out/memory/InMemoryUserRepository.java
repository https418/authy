package io.github.https418.authy.adapter.out.memory;

import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.port.out.UserRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public Mono<User> save(User user) {
        users.put(user.username(), user);
        return Mono.just(user);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return Mono.just(users.containsKey(username));
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return Mono.justOrEmpty(users.get(username));
    }

}
