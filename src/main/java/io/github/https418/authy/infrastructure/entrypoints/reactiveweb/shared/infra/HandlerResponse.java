package io.github.https418.authy.infrastructure.entrypoints.reactiveweb.shared.infra;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@UtilityClass
public class HandlerResponse {

    public static Mono<ServerResponse> createSuccessResponse(HttpStatus status, String message) {
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("message", message));
    }

    public static Mono<ServerResponse> createErrorResponse(HttpStatus status, String error) {
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("error", error));
    }

}
