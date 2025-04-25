package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.application;

import io.github.https418.authy.domain.model.shared.model.common.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.usecase.signup.SignupUseCase;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.mapper.HadlerRequestSignup;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SignupHandler {

    private final SignupUseCase useCase;

    public Mono<ResponseEntity<String>> handle(SignupRequest request) {
        return useCase.signUp(HadlerRequestSignup.prepareSignUpCommand(request))
                .then(Mono.just(ResponseEntity.ok("Usuario registrado exitosamente")))
                .onErrorResume(UserAlreadyExistsException.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()))
                );
    }

}
