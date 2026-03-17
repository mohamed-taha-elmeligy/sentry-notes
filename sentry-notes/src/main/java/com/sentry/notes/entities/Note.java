package com.sentry.notes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "notes", indexes = {
        @Index(name = "idx_notes_name",columnList = "title")}
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

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publicNote=" + publicNote +
                ", user=" + user +
                '}';
    }
}
