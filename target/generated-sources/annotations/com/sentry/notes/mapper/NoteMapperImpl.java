package com.sentry.notes.mapper;

import com.sentry.notes.dtos.request.NoteRequest;
import com.sentry.notes.dtos.response.NoteResponse;
import com.sentry.notes.entities.Note;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-23T22:15:20+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public Note toEntity(NoteRequest notesRequest) {
        if ( notesRequest == null ) {
            return null;
        }

        Note.NoteBuilder note = Note.builder();

        note.title( notesRequest.title() );
        note.content( notesRequest.content() );
        note.publicNote( notesRequest.publicNote() );
        note.user( notesRequest.user() );

        return note.build();
    }

    @Override
    public NoteResponse toResponse(Note note) {
        if ( note == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String content = null;
        Boolean publicNote = null;

        id = note.getId();
        title = note.getTitle();
        content = note.getContent();
        publicNote = note.getPublicNote();

        NoteResponse noteResponse = new NoteResponse( id, title, content, publicNote );

        return noteResponse;
    }
}
