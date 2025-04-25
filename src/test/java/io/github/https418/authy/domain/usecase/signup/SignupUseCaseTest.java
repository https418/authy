package io.github.https418.authy.domain.usecase.signup;

import io.github.https418.authy.domain.model.shared.common.cqrs.Command;
import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.exception.InvalidPasswordException;
import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;
import io.github.https418.authy.domain.model.signup.gateway.SignupChangesGateway;
import io.github.https418.authy.domain.model.signup.gateway.SignupSearchGateway;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import io.github.https418.authy.domain.model.signup.model.exception.InvalidEmailException;
import io.github.https418.authy.domain.model.signup.model.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.signup.value.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

class SignupUseCaseTest {

    @InjectMocks
    private SignupUseCase signupUseCase;

    @Mock
    private SignupSearchGateway signupSearchGateway;

    @Mock
    private SignupChangesGateway signupChangesGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp_UserNotExists() {
        SignupUser signupUser = new SignupUser(
                new Username("testuser"),
                new Email("test@example.com"),
                new Password("Valid@123")
        );
        Command<SignupUser, ContextData> command = new Command<>(signupUser, new ContextData(null, null));

        Mockito.when(signupSearchGateway.existsByUsername(any()))
                .thenReturn(Mono.just(false));
        Mockito.when(signupChangesGateway.register(any()))
                .thenReturn(Mono.empty());

        Mono<Void> result = signupUseCase.signUp(command);

        Assertions.assertDoesNotThrow(() -> result.block());
    }

    @Test
    void testSignUp_UserAlreadyExists() {
        SignupUser signupUser = new SignupUser(
                new Username("testuser"),
                new Email("test@example.com"),
                new Password("Valid@123")
        );
        Command<SignupUser, ContextData> command = new Command<>(signupUser, new ContextData(null, null));

        Mockito.when(signupSearchGateway.existsByUsername(any())).thenReturn(Mono.just(true));

        Mono<Void> result = signupUseCase.signUp(command);

        Assertions.assertThrows(UserAlreadyExistsException.class, result::block);
    }

    @Test
    void testSignUp_InvalidEmail() {
        Assertions.assertThrows(InvalidEmailException.class, () -> {
            new SignupUser(
                    new Username("validUsername"),
                    new Email("invalidemail.com"),
                    new Password("Valid@123")
            );
        });
    }

    @Test
    void testSignUp_InvalidPassword() {
        Assertions.assertThrows(InvalidPasswordException.class, () -> {
            new SignupUser(new Username("validUsername"), new Email("validemail@example.com"), new Password("short"));
        });
    }

}
