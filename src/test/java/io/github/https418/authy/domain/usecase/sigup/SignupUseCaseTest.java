package io.github.https418.authy.domain.usecase.sigup;

import io.github.https418.authy.domain.model.shared.model.common.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;
import io.github.https418.authy.domain.model.signup.gateway.SignupGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import io.github.https418.authy.domain.model.signup.value.Email;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.application.SignupHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class SignupUseCaseTest {

    @Mock
    private SignupGateway useCase;

    @InjectMocks
    private SignupHandler handler;

    @Test
    void shouldRegisterNewUser() {
        var username = new Username("john");
        var email = new Email("john@mail.com");
        var password = new Password("Test@1234");
        var user = new SignupUserRecord(username, email, password);

        Mockito.when(useCase.signUp(user)).thenReturn(Mono.just(user));

        StepVerifier.create(handler.handle(user))
                .expectNext(user)
                .verifyComplete();

        Mockito.verify(useCase).signUp(user);
    }

    @Test
    void shouldThrowWhenUserAlreadyExists() {
        var username = new Username("john");
        var email = new Email("john@mail.com");
        var password = new Password("Test@1234");
        var user = new SignupUserRecord(username, email, password);

        Mockito.when(useCase.signUp(user)).thenReturn(Mono.error(new UserAlreadyExistsException("Usuario ya registrado")));

        StepVerifier.create(handler.handle(user))
                .expectError(UserAlreadyExistsException.class)
                .verify();

        Mockito.verify(useCase).signUp(user);
    }

}
