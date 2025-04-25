package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.request;

import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank String username,
        @Email String email,
        @Size(min = 8) String password
) {
    public SignupUser toDomain() {
        return new SignupUser(
                new Username(username()),
                new io.github.https418.authy.domain.model.signup.value.Email(email()),
                new Password(password())
        );
    }
}
