package io.github.https418.authy.domain.usecase.signin;

import io.github.https418.authy.domain.model.signin.gateway.SigninUserGateway;
import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.gateway.UserRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SigninUseCase implements SigninUserGateway {

    private final UserRepositoryGateway repository;

    @Override
    public Mono<SigninUserRecord> signIn(SigninUserRecord user) {
        return repository.findByUsername(user.username().value())
                .filter(foundUser -> foundUser.password().value().equals(user.password().value()));
    }

}
