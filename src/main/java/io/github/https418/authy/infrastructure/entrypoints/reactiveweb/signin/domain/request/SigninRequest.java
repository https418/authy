package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.request;

import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;
import io.github.https418.authy.domain.model.signin.model.SigninUser;

public record SigninRequest(String username, String password) {

    public SigninUser toDomain() {
        return new SigninUser(
                new Username(username()),
                new Password(password())
        );
    }

}
