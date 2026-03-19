package com.sentry.notes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "notes", indexes = {
        @Index(name = "idx_notes_name",columnList = "title")},
        uniqueConstraints = @UniqueConstraint(name = "user_title", columnNames = {"title","user_id"})
)
@Entity()

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Note{

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(name ="title", nullable = false, length = 200)
    private String title;

    @Setter @Column(name ="content")
    private String content;

    @Setter @Column(name ="is_public", nullable = false)
    private Boolean publicNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void partialUpdate(Note note){
        if(note.title != null && !note.title.isBlank())
            this.title = note.title;

        if(note.content != null && !note.content.isBlank())
            this.content = note.content;

        this.publicNote = note.publicNote;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Note notes)) return false;
        return Objects.equals(id, notes.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
