package com.sentry.notes.appservice;

import com.sentry.notes.dtos.request.UserRequest;
import com.sentry.notes.dtos.response.UserResponse;
import com.sentry.notes.entities.User;
import com.sentry.notes.mapper.UserMapper;
import com.sentry.notes.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAppService {

    private UserService userService;
    private UserMapper userMapper;

    public Page<UserResponse> getAllUsers(int numberOfPage, int numberOfSize){
        return userMapper.toResponse(
                userService.getAllUsers(numberOfPage,numberOfSize)
        );
    }

    public UserResponse findByUserName(String username){
        return userMapper.toResponse(
                userService.findByUserName(username)
        );
    }

    public UserResponse createUser(UserRequest userRequest){
        return userMapper.toResponse(
                userService.createUser(
                        userMapper.toEntity(userRequest)
                )
        );
    }

    public UserResponse updateUser(Long userId, UserRequest userRequest){
        User userUpdateInfo = userMapper.toEntity(userRequest);
        userUpdateInfo.setId(userId);
        return userMapper.toResponse(
                userService.updateUser(userUpdateInfo)
        );
    }

    public UserResponse deleteUser(Long id){
        return userMapper.toResponse(
                userService.deleteUser(id)
        );
    }
}
