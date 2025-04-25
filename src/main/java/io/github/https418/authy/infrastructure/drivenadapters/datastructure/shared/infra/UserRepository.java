package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra;

import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<Boolean> existsByUsername(String username);

    Mono<User> findByUsername(String username);

}
