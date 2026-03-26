package com.sentry.notes.exceptions.response;

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
    private final int status;
    private final String traceId;
    private final String path;

    public ApiResponse(@NonNull String message, HttpStatus status, String path) {
        this.message = message;
        this.error = status.getReasonPhrase();
        this.path = path;
        this.timestamp = Instant.now().toString();
        this.status = status.value();
        this.traceId = UUID.randomUUID().toString();
    }

    public ApiResponse(@NonNull String message,HttpStatus status) {
        this(message,status,null);
    }
}
