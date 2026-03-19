package com.sentry.notes.appservice;

import com.sentry.notes.dtos.request.NoteRequest;
import com.sentry.notes.dtos.response.NoteResponse;
import com.sentry.notes.mapper.NoteMapper;
import com.sentry.notes.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NoteAppService {

    private NoteService noteService;
    private NoteMapper noteMapper;

    public NoteResponse createNote(NoteRequest noteRequest){
        return noteMapper.toResponse(
                noteService.createNote(
                        noteMapper.toEntity(noteRequest)
                )
        );
    }

    public NoteResponse updateNote(NoteRequest noteRequest){
        return noteMapper.toResponse(
                noteService.updateNote(
                        noteMapper.toEntity(noteRequest)
                )
        );
    }

    public Page<NoteResponse> getPublicNotes(int numberOfPage, int numberOfSize){
        return noteMapper.toResponse(
                noteService.getPublicNotes(numberOfPage,numberOfSize)
        );
    }

    public Page<NoteResponse> findByUserId(Long userId, int numberOfPage, int numberOfSize){
        return noteMapper.toResponse(
                noteService.findByUserId(userId, numberOfPage, numberOfSize)
        );
    }

    public NoteResponse findByTitleAndUserId(String title, Long userId){
        return noteMapper.toResponse(
                noteService.findByTitleAndUserId(title, userId)
        );
    }

    public NoteResponse deleteNote(String title, Long userId){
        return noteMapper.toResponse(
                noteService.deleteNote(title, userId)
        );
    }
}
