package io.github.https418.authy.application.signin.query;

import io.github.https418.authy.domain.model.valueobject.Password;
import io.github.https418.authy.domain.model.valueobject.Username;

public record SignInUserQuery(Username username, Password password) {
}
