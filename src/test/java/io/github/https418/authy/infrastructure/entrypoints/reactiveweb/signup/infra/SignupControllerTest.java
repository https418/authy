package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.infra;

import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.application.SignupHandler;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

class SignupControllerTest {

    @InjectMocks
    private SignupController signupController;

    @Mock
    private SignupHandler signupHandler;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(signupController).build();
    }

    @Test
    void testSignUp_Success() {
        Mockito.when(signupHandler.handle(any())).thenReturn(Mono.just(
                ResponseEntity.ok("Usuario registrado exitosamente")
        ));

        webTestClient.post()
                .uri("/api/signup")
                .body(BodyInserters.fromValue(new SignupRequest(
                        "username",
                        "validemail@example.com",
                        "Valid@123"
                )))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Usuario registrado exitosamente");
    }

    @Test
    void testSignUp_InvalidEmail() {
        webTestClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "username": "username",
                            "email": "invalidemail.com",
                            "password": "Valid@123"
                        }
                        """)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testSignUp_InvalidPassword() {
        webTestClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "username": "username",
                            "email": "validemail@example.com",
                            "password": "short"
                        }
                        """)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testSignUp_UserAlreadyExists() {
        Mockito.when(signupHandler.handle(any())).thenReturn(Mono.just(
                ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya se encuentra registrado.")
        ));

        webTestClient.post()
                .uri("/api/signup")
                .body(BodyInserters.fromValue(new SignupRequest(
                        "existingUsername",
                        "validemail@example.com",
                        "Valid@123"
                )))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class).isEqualTo("El usuario ya se encuentra registrado.");
    }

}
