package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.application;

import io.github.https418.authy.domain.model.signin.gateway.SigninUserGateway;
import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SigninHandler {

    private final SigninUserGateway userService;

    public Mono<SigninUserRecord> handle(SigninUserRecord query) {
        return userService.signIn(query);
    }

}
