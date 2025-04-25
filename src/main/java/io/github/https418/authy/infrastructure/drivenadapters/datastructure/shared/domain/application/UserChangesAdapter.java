package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.application;

import io.github.https418.authy.domain.model.shared.cqrs.Command;
import io.github.https418.authy.domain.model.shared.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.gateway.UserChangesGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.application.mapper.UserMapper;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserChangesAdapter implements UserChangesGateway {

    private final UserRepository repository;

    @Override
    public Mono<Void> save(Command<SignupUserRecord, ContextData> command) {
        return repository.save(UserMapper.toData(command)).then();
    }

}
