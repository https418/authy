package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.application;

import io.github.https418.authy.domain.model.signin.model.exception.InvalidCredentialsException;
import io.github.https418.authy.domain.usecase.signin.SigninUseCase;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.mapper.HadlerRequestSignin;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.request.SigninRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SigninHandler {

    private final SigninUseCase useCase;

    public Mono<ResponseEntity<String>> handle(SigninRequest request) {
        return useCase.signIn(HadlerRequestSignin.prepareSignInQuery(request))
                .then(Mono.just(ResponseEntity.ok("Usuario autenticado exitosamente")))
                .onErrorResume(InvalidCredentialsException.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()))
                );
    }

}
