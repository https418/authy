package io.github.https418.authy.domain.usecase;

import io.github.https418.authy.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserCommandUseCase {

    Mono<User> signUp(User user);

}
