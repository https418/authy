package io.github.https418.authy.domain.model.shared.common.cqrs;

public record Query<T, C>(T payload, C context) {

    public static <T, C> Query<T, C> value(T payload, C context) {
        return new Query<>(payload, context);
    }

}
