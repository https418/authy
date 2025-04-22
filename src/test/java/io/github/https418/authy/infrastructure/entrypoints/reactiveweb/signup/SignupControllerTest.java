package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup;

import io.github.https418.authy.application.AuthyApplication;
import io.github.https418.authy.domain.model.shared.model.common.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.application.SignupHandler;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.infra.SignupController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(SignupController.class)
@ContextConfiguration(classes = AuthyApplication.class)
class SignupControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SignupHandler handler;

    @Test
    void shouldReturnOkWhenUserIsCreated() {
        Mockito.when(handler.handle(Mockito.any(SignupUserRecord.class)))
                .thenReturn(Mono.empty());

        webTestClient.post().uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "username": "john",
                            "email": "john@mail.com",
                            "password": "Test@1234"
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Usuario registrado exitosamente");
    }

    @Test
    void shouldReturnConflictWhenUserAlreadyExists() {
        Mockito.when(handler.handle(Mockito.any(SignupUserRecord.class)))
                .thenReturn(Mono.error(new UserAlreadyExistsException("El usuario ya existe")));

        webTestClient.post().uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "username": "john",
                            "email": "john@mail.com",
                            "password": "Test@1234"
                        }
                        """)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(String.class).isEqualTo("El usuario ya existe");
    }

}
