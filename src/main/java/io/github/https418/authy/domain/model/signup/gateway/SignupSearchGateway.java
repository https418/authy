package io.github.https418.authy.domain.model.signup.gateway;

import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import reactor.core.publisher.Mono;

public interface SignupSearchGateway {

    Mono<Boolean> existsByUsername(Query<String, ContextData> query);

}
