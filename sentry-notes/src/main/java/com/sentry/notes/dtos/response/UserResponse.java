package com.sentry.notes.dtos.response;

import com.sentry.notes.enums.Role;

import java.util.Set;

public record UserResponse (
        Long id,
        String username,
        Role role,
        Set<NoteResponse> notes)
{}