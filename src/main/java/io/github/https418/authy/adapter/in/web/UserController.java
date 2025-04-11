package io.github.https418.authy.adapter.in.web;

import io.github.https418.authy.adapter.in.web.dto.SignInRequest;
import io.github.https418.authy.adapter.in.web.dto.SignUpRequest;
import io.github.https418.authy.domain.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.port.in.UserUseCase;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userService;

    @PostMapping("/signup")
    public Mono<ResponseEntity<String>> signUp(@Valid @RequestBody SignUpRequest request) {
        return userService.signUp(request.toDomain())
                .map(user -> ResponseEntity.ok("Usuario registrado exitosamente"))
                .onErrorResume(UserAlreadyExistsException.class, exception ->
                        Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage()))
                );
    }

    @PostMapping("/signin")
    public Mono<ResponseEntity<String>> signIn(@RequestBody SignInRequest request) {
        return userService.signIn(request.username(), request.password())
                .map(user -> ResponseEntity.ok("Usuario autenticado exitosamente"))
                .switchIfEmpty(Mono.just(
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas")
                ));
    }

}
