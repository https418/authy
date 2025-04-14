package io.github.https418.authy.domain.repository;

import io.github.https418.authy.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<Boolean> existsByUsername(String username);

    Mono<User> findByUsername(String username);

}
