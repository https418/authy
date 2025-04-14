package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.dto;

import io.github.https418.authy.application.signin.query.SignInUserQuery;

public record SignInRequest(String username, String password) {

    public SignInUserQuery toQuery() {
        return new SignInUserQuery(
                new io.github.https418.authy.domain.model.valueobject.Username(username()),
                new io.github.https418.authy.domain.model.valueobject.Password(password())
        );
    }

}
