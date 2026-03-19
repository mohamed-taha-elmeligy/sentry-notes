package com.sentry.notes.exceptions.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ApiResponse{
    private final String message;
    private final String error;
    private final String timestamp;
    private final int httpStatus;
    private final String traceId;

    @Builder
    public ApiResponse(@NonNull String message,HttpStatus httpStatus) {
        this.message = message;
        this.error = httpStatus.getReasonPhrase();
        this.timestamp = Instant.now().toString();
        this.httpStatus = httpStatus.value();
        this.traceId = UUID.randomUUID().toString();
    }
}
