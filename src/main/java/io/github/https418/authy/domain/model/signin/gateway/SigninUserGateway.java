package io.github.https418.authy.domain.model.signin.gateway;

import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import reactor.core.publisher.Mono;

public interface SigninUserGateway {

    Mono<SigninUserRecord> signIn(SigninUserRecord user);

}
