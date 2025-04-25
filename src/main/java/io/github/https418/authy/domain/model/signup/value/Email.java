package io.github.https418.authy.domain.model.signup.value;

import io.github.https418.authy.domain.model.signup.model.exception.InvalidEmailException;

import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    public Email {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("El correo electrónico no puede estar vacío");

        else if (!EMAIL_REGEX.matcher(value).matches())
            throw new InvalidEmailException("El formato del email es inválido");
    }

}
