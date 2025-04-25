package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.mapper;

import io.github.https418.authy.domain.model.shared.common.cqrs.Command;
import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.signup.model.SignupUser;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signup.domain.request.SignupRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HadlerRequestSignup {

    public static Command<SignupUser, ContextData> prepareSignUpCommand(SignupRequest request) {
        return Command.value(request.toDomain(), new ContextData(null, null));
    }

}
