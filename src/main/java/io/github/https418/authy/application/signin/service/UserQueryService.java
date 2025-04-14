package io.github.https418.authy.application.signin.service;

import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.repository.UserRepository;
import io.github.https418.authy.domain.usecase.UserQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase {

    private final UserRepository userRepository;

    @Override
    public Mono<User> signIn(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.password().value().equals(password));
    }
}
