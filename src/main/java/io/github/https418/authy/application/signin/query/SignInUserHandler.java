package io.github.https418.authy.application.signin.query;

import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.usecase.UserQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SignInUserHandler {

    private final UserQueryUseCase userService;

    public Mono<User> handle(SignInUserQuery query) {
        return userService.signIn(
                query.username().value(),
                query.password().value()
        );
    }

}
