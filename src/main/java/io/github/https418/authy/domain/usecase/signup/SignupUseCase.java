package io.github.https418.authy.domain.usecase.signup;

import io.github.https418.authy.domain.model.shared.model.common.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.signup.gateway.SignupGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.gateway.UserRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SignupUseCase implements SignupGateway {

    private final UserRepositoryGateway repository;

    @Override
    public Mono<SignupUserRecord> signUp(SignupUserRecord user) {
        return repository.existsByUsername(user.username().value())
                .flatMap(exist -> {
                    if (Boolean.TRUE.equals(exist))
                        return Mono.error(new UserAlreadyExistsException("El usuario ya se encuentra registrado."));
                    else return repository.save(user);
                });
    }

}
