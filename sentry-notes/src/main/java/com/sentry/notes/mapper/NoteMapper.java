package com.sentry.notes.mapper;

import com.sentry.notes.dtos.request.NoteRequest;
import com.sentry.notes.dtos.response.NoteResponse;
import com.sentry.notes.entities.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "id",ignore = true)
    Note toEntity(NoteRequest notesRequest);

    NoteResponse toResponse(Note note);
}
