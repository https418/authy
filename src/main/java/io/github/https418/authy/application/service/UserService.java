package io.github.https418.authy.application.service;

import io.github.https418.authy.domain.exception.UserAlreadyExistsException;
import io.github.https418.authy.domain.model.User;
import io.github.https418.authy.domain.port.in.UserUseCase;
import io.github.https418.authy.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    @Override
    public Mono<User> signUp(User user) {
        return userRepository.existsByUsername(user.username())
                .flatMap(exist -> {
                    if (Boolean.TRUE.equals(exist))
                        return Mono.error(new UserAlreadyExistsException("El usuario ya se encuentra registrado."));
                    else return userRepository.save(user);
                });
    }

    @Override
    public Mono<User> signIn(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.password().equals(password));
    }
}
