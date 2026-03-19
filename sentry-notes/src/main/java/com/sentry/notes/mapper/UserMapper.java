package com.sentry.notes.mapper;

import com.sentry.notes.dtos.request.UserRequest;
import com.sentry.notes.dtos.response.UserResponse;
import com.sentry.notes.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "notes", ignore = true)
    User toEntity(UserRequest request);

    UserResponse toResponse(User user);
}
