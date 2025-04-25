package io.github.https418.authy.infrastructure.drivenadapters.datastructure.signup.domain.mapper;

import io.github.https418.authy.domain.model.shared.common.cqrs.Command;
import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import io.github.https418.authy.infrastructure.drivenadapters.datastructure.shared.domain.UserData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SignupDataMapper {

    public static UserData toData(Command<SignupUser, ContextData> command) {
        var payload = command.payload();
        return new UserData(payload.username(), payload.email(), payload.password());
    }

}
