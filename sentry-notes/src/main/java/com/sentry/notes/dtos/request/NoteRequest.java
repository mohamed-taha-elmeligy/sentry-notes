package com.sentry.notes.dtos.request;

import com.sentry.notes.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoteRequest (
        @Size(min = 3, max = 100, message = "The title can range in length  3 to 200 character")
        @NotBlank String title,

        String content,
        @NotNull Boolean publicNote,
        @NotNull User user)
{}