package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra;

import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.UserData;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<UserData> save(UserData userData);

    Mono<Boolean> existsByUsername(String username);

    Mono<UserData> findByUsername(String username);

}
