package io.github.https418.authy.domain.model.signup.gateway;

import io.github.https418.authy.domain.model.shared.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.cqrs.Query;
import reactor.core.publisher.Mono;

public interface UserSearchGateway {

    Mono<Boolean> existsByUsername(Query<String, ContextData> query);

}
