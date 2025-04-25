package io.github.https418.authy.domain.usecase.signup;

import io.github.https418.authy.domain.model.shared.common.cqrs.Command;
import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.signup.model.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.signup.gateway.SignupChangesGateway;
import io.github.https418.authy.domain.model.signup.gateway.SignupSearchGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SignupUseCase {

    private final SignupSearchGateway signupSearchGateway;
    private final SignupChangesGateway signupChangesGateway;

    public Mono<Void> signUp(Command<SignupUser, ContextData> command) {
        return signupSearchGateway.existsByUsername(Query.value(command.payload().username().value(), command.context()))
                .flatMap(exists -> exists
                        ? Mono.error(new UserAlreadyExistsException("El usuario ya se encuentra registrado."))
                        : signupChangesGateway.register(command)
                );
    }

}
