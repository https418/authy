package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain;

import io.github.https418.authy.domain.model.signup.value.Email;
import io.github.https418.authy.domain.model.shared.common.value.Password;
import io.github.https418.authy.domain.model.shared.common.value.Username;

public record UserData(Username username, Email email, Password password) {
}
