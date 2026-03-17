package com.sentry.notes.dtos.response;

public record NoteResponse(
        Long id,
        String title,
        String content,
        Boolean publicNote)
{}