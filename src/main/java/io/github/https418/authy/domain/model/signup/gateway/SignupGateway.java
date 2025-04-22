package io.github.https418.authy.domain.model.signup.gateway;

import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import reactor.core.publisher.Mono;

public interface SignupGateway {

    Mono<SignupUserRecord> signUp(SignupUserRecord user);

}
