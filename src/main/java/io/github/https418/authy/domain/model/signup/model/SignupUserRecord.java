package io.github.https418.authy.domain.model.signup.model;

import io.github.https418.authy.domain.model.signup.value.Email;
import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;

public record SignupUserRecord(Username username, Email email, Password password) {
}
