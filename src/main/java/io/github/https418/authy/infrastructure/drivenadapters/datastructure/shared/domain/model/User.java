package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.model;

import io.github.https418.authy.domain.model.signup.value.Email;
import io.github.https418.authy.domain.model.shared.value.Password;
import io.github.https418.authy.domain.model.shared.value.Username;

public record User(Username username, Email email, Password password) {
}
