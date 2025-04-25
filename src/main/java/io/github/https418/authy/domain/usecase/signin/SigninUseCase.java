package io.github.https418.authy.domain.usecase.signin;

import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.signin.model.exception.InvalidCredentialsException;
import io.github.https418.authy.domain.model.signin.gateway.SigninSearchGateway;
import io.github.https418.authy.domain.model.signin.model.SigninUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SigninUseCase {

    private final SigninSearchGateway signinSearchGateway;

    public Mono<Void> signIn(Query<SigninUser, ContextData> query) {
        return signinSearchGateway.findUser(query)
                .switchIfEmpty(Mono.error(new InvalidCredentialsException("Credenciales inválidas")))
                .then();
    }

}
