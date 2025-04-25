package io.github.https418.authy.domain.model.shared.cqrs;

public record Command<T, C>(T payload, C context) {

    public static <T, C> Command<T, C> value(T payload, C context) {
        return new Command<>(payload, context);
    }

}
