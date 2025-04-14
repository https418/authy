package io.github.https418.authy.application.signup.command;

import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.usecase.UserCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SignUpUserHandler {

    private final UserCommandUseCase userService;

    public Mono<User> handle(SignUpUserCommand command) {
        return userService.signUp(new User(command.username(), command.email(), command.password()));
    }

}
