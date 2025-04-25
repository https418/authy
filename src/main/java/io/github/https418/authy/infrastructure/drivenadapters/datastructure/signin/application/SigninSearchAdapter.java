package io.github.https418.authy.infrastructure.drivenadapters.datastructure.signin.application;

import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.signin.gateway.SigninSearchGateway;
import io.github.https418.authy.domain.model.signin.model.SigninUser;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SigninSearchAdapter implements SigninSearchGateway {

    private final UserRepository repository;

    @Override
    public Mono<SigninUser> findUser(Query<SigninUser, ContextData> query) {
        var user = query.payload();
        return repository.findByUsername(user.username().value())
                .filter(foundUser -> foundUser.password().value().equals(user.password().value()))
                .map(validatedUser -> new SigninUser(validatedUser.username(), validatedUser.password()));
    }

}
