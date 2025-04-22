package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.request;

import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;
import io.github.https418.authy.domain.model.signin.model.SigninUserRecord;

public record SigninRequest(String username, String password) {

    public SigninUserRecord toDomain() {
        return new SigninUserRecord(
                new Username(username()),
                new Password(password())
        );
    }

}
