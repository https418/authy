package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin;

import io.github.https418.authy.application.AuthyApplication;
import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;
import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.application.SigninHandler;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.infra.SigninController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(SigninController.class)
@ContextConfiguration(classes = AuthyApplication.class)
class SigninControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SigninHandler handler;

    @Test
    void shouldReturnOkWhenCredentialsAreValid() {
        var username = new Username("john");
        var password = new Password("Test@1234");
        var user = new SigninUserRecord(username, password);

        Mockito.when(handler.handle(Mockito.any(SigninUserRecord.class))).thenReturn(Mono.just(user));

        webTestClient.post().uri("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "username": "john",
                            "password": "Test@1234"
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Usuario autenticado exitosamente");
    }

    @Test
    void shouldReturnUnauthorizedWhenCredentialsAreInvalid() {
        Mockito.when(handler.handle(Mockito.any(SigninUserRecord.class))).thenReturn(Mono.empty());

        webTestClient.post().uri("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                            "username": "john",
                            "password": "wrongPass@123"
                        }
                        """)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(String.class).isEqualTo("Credenciales inválidas");
    }

}
