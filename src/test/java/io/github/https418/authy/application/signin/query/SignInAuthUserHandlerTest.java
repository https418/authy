package io.github.https418.authy.application.signin.query;

import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.model.valueobject.Email;
import io.github.https418.authy.domain.model.valueobject.Password;
import io.github.https418.authy.domain.model.valueobject.Username;
import io.github.https418.authy.domain.usecase.UserQueryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class SignInAuthUserHandlerTest {

    @Mock
    private UserQueryUseCase useCase;

    @InjectMocks
    private SignInUserHandler handler;

    @Test
    void shouldSignInUserWithCorrectCredentials() {
        var username = new Username("john");
        var email = new Email("john@mail.com");
        var password = new Password("Test@1234");
        User authUser = new User(username, email, password);
        SignInUserQuery query = new SignInUserQuery(username, password);

        Mockito.when(useCase.signIn("john", "Test@1234")).thenReturn(Mono.just(authUser));

        StepVerifier.create(handler.handle(query))
                .expectNext(authUser)
                .verifyComplete();
    }

    @Test
    void shouldReturnEmptyWhenCredentialsInvalid() {
        var username = new Username("john");
        var password = new Password("wrongPass@123");
        SignInUserQuery query = new SignInUserQuery(username, password);

        Mockito.when(useCase.signIn("john", "wrongPass@123")).thenReturn(Mono.empty());

        StepVerifier.create(handler.handle(query)).verifyComplete();
    }

}
