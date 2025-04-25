package io.github.https418.authy.infrastructure.drivenadapters.datastructure.signup.application;

import io.github.https418.authy.domain.model.shared.common.cqrs.Command;
import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.gateway.SignupChangesGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.signup.domain.mapper.SignupDataMapper;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SignupChangesAdapter implements SignupChangesGateway {

    private final UserRepository repository;

    @Override
    public Mono<Void> register(Command<SignupUser, ContextData> command) {
        return repository.save(SignupDataMapper.toData(command)).then();
    }

}
