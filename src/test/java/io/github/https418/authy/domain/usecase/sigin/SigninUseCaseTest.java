package io.github.https418.authy.domain.usecase.sigin;

import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;
import io.github.https418.authy.domain.model.signin.gateway.SigninUserGateway;
import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.application.SigninHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class SigninUseCaseTest {

    @Mock
    private SigninUserGateway useCase;

    @InjectMocks
    private SigninHandler handler;

    @Test
    void shouldSignInUserWithCorrectCredentials() {
        var username = new Username("john");
        var password = new Password("Test@1234");
        SigninUserRecord user = new SigninUserRecord(username, password);

        Mockito.when(useCase.signIn(user)).thenReturn(Mono.just(user));

        StepVerifier.create(handler.handle(user))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    void shouldReturnEmptyWhenCredentialsInvalid() {
        var username = new Username("john");
        var password = new Password("wrongPass@123");
        SigninUserRecord user = new SigninUserRecord(username, password);

        Mockito.when(useCase.signIn(user)).thenReturn(Mono.empty());

        StepVerifier.create(handler.handle(user)).verifyComplete();
    }

}
