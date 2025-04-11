package io.github.https418.authy.adapter.in.web.dto;

import io.github.https418.authy.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank String username,
        @Email String email,
        @Size(min = 8) String password
) {
    public User toDomain() {
        return new User(username, email, password);
    }
}
