package io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.application.mapper;

import io.github.https418.authy.domain.model.shared.cqrs.Command;
import io.github.https418.authy.domain.model.shared.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.model.SignupUserRecord;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toData(Command<SignupUserRecord, ContextData> command) {
        var payload = command.payload();
        return new User(payload.username(), payload.email(), payload.password());
    }

    public static SignupUserRecord toModel(User user) {
        return new SignupUserRecord(user.username(), user.email(), user.password());
    }

}
