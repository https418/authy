package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.infra;

import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.application.SigninHandler;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.request.SigninRequest;
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
public class SigninController {

    private final SigninHandler signinHandler;

    @PostMapping("/signin")
    public Mono<ResponseEntity<String>> signIn(@RequestBody SigninRequest request) {
        return signinHandler.handle(request.toDomain())
                .map(user -> ResponseEntity.ok("Usuario autenticado exitosamente"))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas")));
    }

}
