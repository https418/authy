package io.github.https418.authy.application.signup.service;

import io.github.https418.authy.domain.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.repository.UserRepository;
import io.github.https418.authy.domain.usecase.UserCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCommandService implements UserCommandUseCase {

    private final UserRepository userRepository;

    @Override
    public Mono<User> signUp(User user) {
        return userRepository.existsByUsername(user.username().value())
                .flatMap(exist -> {
                    if (Boolean.TRUE.equals(exist))
                        return Mono.error(new UserAlreadyExistsException("El usuario ya se encuentra registrado."));
                    else return userRepository.save(user);
                });
    }

}
