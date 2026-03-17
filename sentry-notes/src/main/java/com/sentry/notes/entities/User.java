package com.sentry.notes.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;


@Table(name = "users", indexes = {
        @Index(name = "idx_user_username",columnList = "username"),
        @Index(name = "idx_user_password",columnList = "password")
})
@Entity()

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements Comparable<User>{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", updatable = false, insertable = false)
    private Long id;

    @Setter @Column(name ="username", nullable = false)
    private String username;

    @Setter @Column(name ="password", nullable = false)
    private String password;

    @Setter
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "id")
    private Set<Notes> notes;

    @Override
    public int compareTo(@NonNull User ather) {
        return this.id.compareTo(ather.getId());
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
