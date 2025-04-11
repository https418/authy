package io.github.https418.authy.domain.port.in;

import io.github.https418.authy.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserUseCase {

    Mono<User> signUp(User user);

    Mono<User> signIn(String username, String password);

}
