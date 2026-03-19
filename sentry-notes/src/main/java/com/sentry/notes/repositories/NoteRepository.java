package com.sentry.notes.repositories;

import com.sentry.notes.entities.Note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    Page<Note> getByPublicNoteTrue(Pageable pageable);
    Page<Note> findByUserId(Long userId, Pageable pageable);

    Optional<Note> findByTitleAndUserId(String title, Long userId);

}
