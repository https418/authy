package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.mapper;


import io.github.https418.authy.domain.model.shared.common.cqrs.ContextData;
import io.github.https418.authy.domain.model.shared.common.cqrs.Query;
import io.github.https418.authy.domain.model.signin.model.SigninUser;
import io.github.https418.authy.infrastructure.entrypoints.reactiveweb.signin.domain.request.SigninRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HadlerRequestSignin {

    public static Query<SigninUser, ContextData> prepareSignInQuery(SigninRequest request) {
        return Query.value(request.toDomain(), new ContextData(null, null));
    }

}
