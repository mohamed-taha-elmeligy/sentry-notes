package com.sentry.notes.mapper;

import com.sentry.notes.dtos.request.UserRequest;
import com.sentry.notes.dtos.response.UserResponse;
import com.sentry.notes.entities.Note;
import com.sentry.notes.entities.User;
import com.sentry.notes.enums.Role;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-20T22:39:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.username() );
        user.password( request.password() );

        return user.build();
    }

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        Role role = null;
        Set<Note> notes = null;

        id = user.getId();
        username = user.getUsername();
        role = user.getRole();
        Set<Note> set = user.getNotes();
        if ( set != null ) {
            notes = new LinkedHashSet<Note>( set );
        }

        UserResponse userResponse = new UserResponse( id, username, role, notes );

        return userResponse;
    }
}
