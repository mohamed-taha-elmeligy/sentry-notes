package com.sentry.notes.entities;

import com.sentry.notes.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Table(name = "users", indexes = {
        @Index(name = "idx_user_username",columnList = "username")}
)
@Entity()

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    private Long id;

    @Setter @Column(name ="username", nullable = false, length = 100)
    private String username;

    @Setter @Column(name ="password", nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Note> notes = new HashSet<>();

    @Builder
    public User(@NonNull String username,
                @NonNull String password, @NonNull Role role, Set<Note> notes) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.notes = notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void partialUpdate(User updatedInfo) {
        if (updatedInfo.username != null && !updatedInfo.username.isBlank()) {
            this.username = updatedInfo.username;
        }
        if (updatedInfo.password != null && !updatedInfo.password.isBlank()) {
            this.password = updatedInfo.password;
        }

        if (updatedInfo.role != null) {
            this.role = updatedInfo.role;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
