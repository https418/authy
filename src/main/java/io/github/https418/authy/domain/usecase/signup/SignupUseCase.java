package io.github.https418.authy.domain.usecase.signup;

import io.github.https418.authy.domain.model.shared.cqrs.Command;
import io.github.https418.authy.domain.model.shared.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.cqrs.Query;
import io.github.https418.authy.domain.model.shared.model.common.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.signup.gateway.UserChangesGateway;
import io.github.https418.authy.domain.model.signup.gateway.UserSearchGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SignupUseCase {

    private final UserSearchGateway userSearchGateway;
    private final UserChangesGateway userChangesGateway;

    public Mono<Void> signUp(Command<SignupUserRecord, ContextData> command) {
        return userSearchGateway.existsByUsername(Query.value(command.payload().username().value(), command.context()))
                .flatMap(exists -> exists
                        ? Mono.error(new UserAlreadyExistsException("El usuario ya se encuentra registrado."))
                        : userChangesGateway.save(command)
                );
    }

}
