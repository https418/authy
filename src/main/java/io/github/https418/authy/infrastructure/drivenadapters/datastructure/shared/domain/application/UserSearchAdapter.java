package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.application;

import io.github.https418.authy.domain.model.shared.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.cqrs.Query;
import io.github.https418.authy.domain.model.signup.gateway.UserSearchGateway;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserSearchAdapter implements UserSearchGateway {

    private final UserRepository repository;

    @Override
    public Mono<Boolean> existsByUsername(Query<String, ContextData> query) {
        return repository.existsByUsername(query.payload());
    }

}
