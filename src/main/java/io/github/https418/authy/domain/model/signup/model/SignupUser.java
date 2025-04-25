package io.github.https418.authy.domain.model.signup.model;

import io.github.https418.authy.domain.model.signup.value.Email;
import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;

public record SignupUser(Username username, Email email, Password password) {
}
