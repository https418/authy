package io.github.https418.authy.application.signup.command;

import io.github.https418.authy.domain.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.model.valueobject.Email;
import io.github.https418.authy.domain.model.valueobject.Password;
import io.github.https418.authy.domain.model.valueobject.Username;
import io.github.https418.authy.domain.usecase.UserCommandUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class SignUpAuthUserHandlerTest {

    @Mock
    private UserCommandUseCase useCase;

    @InjectMocks
    private SignUpUserHandler handler;

    @Test
    void shouldRegisterNewUser() {
        var username = new Username("john");
        var email = new Email("john@mail.com");
        var password = new Password("Test@1234");
        User authUser = new User(username, email, password);
        SignUpUserCommand command = new SignUpUserCommand(username, email, password);

        Mockito.when(useCase.signUp(authUser)).thenReturn(Mono.just(authUser));

        StepVerifier.create(handler.handle(command))
                .expectNext(authUser)
                .verifyComplete();

        Mockito.verify(useCase).signUp(authUser);
    }

    @Test
    void shouldThrowWhenUserAlreadyExists() {
        var username = new Username("john");
        var email = new Email("john@mail.com");
        var password = new Password("Test@1234");
        User authUser = new User(username, email, password);
        SignUpUserCommand command = new SignUpUserCommand(username, email, password);

        Mockito.when(useCase.signUp(authUser)).thenReturn(Mono.error(new UserAlreadyExistsException("Usuario ya registrado")));

        StepVerifier.create(handler.handle(command))
                .expectError(UserAlreadyExistsException.class)
                .verify();

        Mockito.verify(useCase).signUp(authUser);
    }

}
