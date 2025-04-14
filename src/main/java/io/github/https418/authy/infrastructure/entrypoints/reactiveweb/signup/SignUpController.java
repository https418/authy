package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup;

import io.github.https418.authy.application.signup.command.SignUpUserCommand;
import io.github.https418.authy.application.signup.command.SignUpUserHandler;
import io.github.https418.authy.domain.exception.UserAlreadyExistsException;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.dto.SignUpRequest;
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
public class SignUpController {

    private final SignUpUserHandler signUpUserHandler;

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signUp(@Valid @RequestBody SignUpRequest request) {
        SignUpUserCommand command = request.toCommand();
        return signUpUserHandler.handle(command)
                .thenReturn(ResponseEntity.ok("Usuario registrado exitosamente"))
                .onErrorResume(UserAlreadyExistsException.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()))
                );
    }

}
