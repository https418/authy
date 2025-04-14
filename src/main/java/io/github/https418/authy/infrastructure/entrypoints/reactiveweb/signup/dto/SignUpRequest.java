package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.dto;

import io.github.https418.authy.application.signup.command.SignUpUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank String username,
        @Email String email,
        @Size(min = 8) String password
) {
    public SignUpUserCommand toCommand() {
        return new SignUpUserCommand(
                new io.github.https418.authy.domain.model.valueobject.Username(username()),
                new io.github.https418.authy.domain.model.valueobject.Email(email()),
                new io.github.https418.authy.domain.model.valueobject.Password(password())
        );
    }
}
