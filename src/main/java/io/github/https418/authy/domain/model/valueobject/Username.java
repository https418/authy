package io.github.https418.authy.domain.model.valueobject;

public record Username(String value) {

    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
    }

}
