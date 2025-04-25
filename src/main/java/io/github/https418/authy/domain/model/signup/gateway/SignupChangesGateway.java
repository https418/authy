package io.github.https418.authy.domain.model.signup.gateway;

import io.github.https418.authy.domain.model.shared.common.cqrs.Command;
import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import reactor.core.publisher.Mono;

public interface SignupChangesGateway {

    Mono<Void> register(Command<SignupUser, ContextData> command);

}
