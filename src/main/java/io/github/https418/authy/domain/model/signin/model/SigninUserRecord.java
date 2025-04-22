package io.github.https418.authy.domain.model.signin.model;

import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;

public record SigninUserRecord(Username username, Password password) {
}
