package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.application;

import io.github.https418.authy.domain.model.signup.gateway.SignupGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SignupHandler {

    private final SignupGateway userService;

    public Mono<SignupUserRecord> handle(SignupUserRecord command) {
        return userService.signUp(command);
    }

}
