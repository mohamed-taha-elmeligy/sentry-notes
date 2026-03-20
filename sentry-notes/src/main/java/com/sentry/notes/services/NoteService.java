package com.sentry.notes.services;

import com.sentry.notes.config.PageableConfig;
import com.sentry.notes.entities.Note;
import com.sentry.notes.exceptions.ResourceNotFoundException;
import com.sentry.notes.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class NoteService {
    private NoteRepository noteRepository;
    private static final String TITLE = "title";
    private static final String NOTE_NOT_FOUND = "Note not found with title: ";

    public Note createNote(Note note){
        return noteRepository.save(note);
    }

    @Transactional
    public Note updateNote(Note note){
        return  noteRepository.findByTitleAndUserId(note.getTitle(), note.getUser().getId())
                .map(existNote ->{
                    existNote.partialUpdate(note);
                    return existNote;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(NOTE_NOT_FOUND+ note.getTitle())
                );
    }

    @Transactional(readOnly = true)
    public Page<Note> getPublicNotes(int numberOfPage, int numberOfSize){
        return noteRepository.
                getByPublicNoteTrue(
                        PageableConfig.pageable(numberOfPage,numberOfSize,TITLE)
                );
    }

    @Transactional(readOnly = true)
    public Page<Note> findByUserId(Long userId, int numberOfPage, int numberOfSize){
        return noteRepository.findByUserId(userId,
                PageableConfig.pageable(numberOfPage,numberOfSize,TITLE)
        );
    }

    @Transactional(readOnly = true)
    public Note findByTitleAndUserId(String title, Long userId){
        return noteRepository.findByTitleAndUserId(title, userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(NOTE_NOT_FOUND + title)
                );
    }

    @Transactional
    public Note deleteNote(String title, Long userId){
        return noteRepository.findByTitleAndUserId(title,userId)
                .map(note -> {
                    noteRepository.delete(note);
                    return note;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(NOTE_NOT_FOUND + title)
                );
    }


}
