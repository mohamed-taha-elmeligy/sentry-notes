package com.sentry.notes.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest (
        @Size(min = 3, max = 100, message = "The username can range in length  3 to 100 character")
        @NotBlank String username,

        @Size(min = 8, max = 64, message = "The password can range in length 8 to 64 character")
        @NotBlank String password)
{}