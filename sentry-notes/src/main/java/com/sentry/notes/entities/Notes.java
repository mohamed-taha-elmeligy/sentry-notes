package com.sentry.notes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "notes", indexes = {
        @Index(name = "idx_notes_name",columnList = "name")}
)
@Entity()

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Notes implements Comparable<Notes> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(name ="name", nullable = false)
    private String name;

    @Setter @Column(name ="content")
    private String content;

    @Setter @Column(name ="is_public", nullable = false)
    private boolean isPublic;

    @Setter
    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "")
    private User user;



    @Override
    public int compareTo(@NonNull Notes ather) {
        return this.id.compareTo(ather.getId());
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Notes notes)) return false;
        return Objects.equals(id, notes.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
