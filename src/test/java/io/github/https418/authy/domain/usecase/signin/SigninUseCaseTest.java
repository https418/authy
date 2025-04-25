package io.github.https418.authy.domain.usecase.signin;

import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;
import io.github.https418.authy.domain.model.signin.gateway.SigninSearchGateway;
import io.github.https418.authy.domain.model.signin.model.SigninUser;
import io.github.https418.authy.domain.model.signin.model.exception.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SigninUseCaseTest {

    @InjectMocks
    private SigninUseCase signinUseCase;

    @Mock
    private SigninSearchGateway signinSearchGateway;

    private SigninUser validUser;

    @BeforeEach
    void setUp() {
        validUser = new SigninUser(
                new Username("testuser"),
                new Password("Valid@123")
        );
    }

    @Test
    void testSignIn_Success() {
        Query<SigninUser, ContextData> query = new Query<>(validUser, new ContextData(null, null));

        Mockito.when(signinSearchGateway.findUser(any()))
                .thenReturn(Mono.just(validUser));

        Mono<Void> result = signinUseCase.signIn(query);

        Assertions.assertDoesNotThrow(() -> result.block());
    }

    @Test
    void testSignIn_UserNotFound() {
        Query<SigninUser, ContextData> query = new Query<>(validUser, new ContextData(null, null));

        Mockito.when(signinSearchGateway.findUser(any()))
                .thenReturn(Mono.empty());

        Mono<Void> result = signinUseCase.signIn(query);

        Assertions.assertThrows(InvalidCredentialsException.class, result::block);
    }

}
