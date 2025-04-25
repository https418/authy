package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.infra;

import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.application.SigninHandler;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.request.SigninRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SigninControllerTest {

    @InjectMocks
    private SigninController signinController;

    @Mock
    private SigninHandler signinHandler;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(signinController).build();
    }

    @Test
    void testSignIn_Success() {
        Mockito.when(signinHandler.handle(any())).thenReturn(Mono.just(
                ResponseEntity.ok("Usuario autenticado exitosamente")
        ));

        webTestClient.post()
                .uri("/api/signin")
                .bodyValue(new SigninRequest("testuser", "Valid@123"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Usuario autenticado exitosamente");
    }

    @Test
    void testSignIn_InvalidCredentials() {
        Mockito.when(signinHandler.handle(any())).thenReturn(Mono.just(
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas")
        ));

        webTestClient.post()
                .uri("/api/signin")
                .bodyValue(new SigninRequest("wronguser", "wrongpass"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(String.class).isEqualTo("Credenciales inválidas");
    }

}
