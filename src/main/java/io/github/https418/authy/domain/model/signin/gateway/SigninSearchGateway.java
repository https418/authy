package io.github.https418.authy.domain.model.signin.gateway;

import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.signin.model.SigninUser;
import reactor.core.publisher.Mono;

public interface SigninSearchGateway {

    Mono<SigninUser> findUser(Query<SigninUser, ContextData> query);

}
