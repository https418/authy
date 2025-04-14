package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin;

import io.github.https418.authy.application.signin.query.SignInUserHandler;
import io.github.https418.authy.application.signin.query.SignInUserQuery;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.dto.SignInRequest;
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
public class SignInController {

    private final SignInUserHandler signInUserHandler;

    @PostMapping("/signin")
    public Mono<ResponseEntity<String>> signIn(@RequestBody SignInRequest request) {
        SignInUserQuery query = request.toQuery();
        return signInUserHandler.handle(query)
                .map(user -> ResponseEntity.ok("Usuario autenticado exitosamente"))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas")));
    }

}
