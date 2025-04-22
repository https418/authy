package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.request;

import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank String username,
        @Email String email,
        @Size(min = 8) String password
) {
    public SignupUserRecord toDomain() {
        return new SignupUserRecord(
                new io.github.https418.authy.domain.model.shared.value.Username(username()),
                new io.github.https418.authy.domain.model.signup.value.Email(email()),
                new io.github.https418.authy.domain.model.shared.value.Password(password())
        );
    }
}
