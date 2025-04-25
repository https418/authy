package io.github.https418.authy.infrastructure.drivenadapters.datastructure.signup.application;

import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.signup.gateway.SignupSearchGateway;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SignupSearchAdapter implements SignupSearchGateway {

    private final UserRepository repository;

    @Override
    public Mono<Boolean> existsByUsername(Query<String, ContextData> query) {
        return repository.existsByUsername(query.payload());
    }

}
