package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin;

import io.github.https418.authy.application.signin.query.SignInUserHandler;
import io.github.https418.authy.application.signin.query.SignInUserQuery;
import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.model.valueobject.Email;
import io.github.https418.authy.domain.model.valueobject.Password;
import io.github.https418.authy.domain.model.valueobject.Username;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(SignInController.class)
class SignInControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SignInUserHandler handler;

    @Test
    void shouldReturnOkWhenCredentialsAreValid() {
        var username = new Username("john");
        var password = new Password("Test@1234");
        var user = new User(username, new Email("john@mail.com"), password);

        Mockito.when(handler.handle(Mockito.any(SignInUserQuery.class))).thenReturn(Mono.just(user));

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
        Mockito.when(handler.handle(Mockito.any(SignInUserQuery.class))).thenReturn(Mono.empty());

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
