package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.infra;

import io.github.https418.authy.domain.model.shared.model.common.exception.UserAlreadyExistsException;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.application.SignupHandler;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.request.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignupController {

    private final SignupHandler signupHandler;

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signUp(@Valid @RequestBody SignupRequest request) {
        return signupHandler.handle(request.toDomain())
                .thenReturn(ResponseEntity.ok("Usuario registrado exitosamente"))
                .onErrorResume(UserAlreadyExistsException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()))
                );
    }

}
