package io.github.https418.authy.domain.model.signup.gateway;

import io.github.https418.authy.domain.model.shared.cqrs.Command;
import io.github.https418.authy.domain.model.shared.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import reactor.core.publisher.Mono;

public interface UserChangesGateway {

    Mono<Void> save(Command<SignupUserRecord, ContextData> command);

}
