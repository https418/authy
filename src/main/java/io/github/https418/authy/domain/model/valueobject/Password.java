package io.github.https418.authy.domain.model.valueobject;

import io.github.https418.authy.domain.exception.InvalidPasswordException;

import java.util.regex.Pattern;

public record Password(String value) {

    private static final Pattern PASSWORD_REGEX = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            Pattern.CASE_INSENSITIVE
    );

    public Password {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("La clave no puede estar vacía");

        else if (!PASSWORD_REGEX.matcher(value).matches())
            throw new InvalidPasswordException("La clave debe tener al menos 8 caracteres, una mayúscula y un carácter especial");
    }

    @Override
    public String toString() {
        return "********";
    }

}
