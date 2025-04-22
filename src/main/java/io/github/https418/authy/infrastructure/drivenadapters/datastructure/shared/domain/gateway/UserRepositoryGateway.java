package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.gateway;

import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import reactor.core.publisher.Mono;

public interface UserRepositoryGateway {

    Mono<SignupUserRecord> save(SignupUserRecord user);

    Mono<Boolean> existsByUsername(String username);

    Mono<SigninUserRecord> findByUsername(String username);

}
