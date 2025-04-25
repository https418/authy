package io.github.https418.authy.domain.model.signin.model;

import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;

public record SigninUser(Username username, Password password) {
}
