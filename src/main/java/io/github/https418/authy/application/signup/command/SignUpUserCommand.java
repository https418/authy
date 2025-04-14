package io.github.https418.authy.application.signup.command;

import io.github.https418.authy.domain.model.valueobject.Email;
import io.github.https418.authy.domain.model.valueobject.Password;
import io.github.https418.authy.domain.model.valueobject.Username;

public record SignUpUserCommand(Username username, Email email, Password password) {
}
