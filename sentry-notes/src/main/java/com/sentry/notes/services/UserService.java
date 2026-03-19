package com.sentry.notes.services;

import com.sentry.notes.config.PageableConfig;
import com.sentry.notes.entities.User;
import com.sentry.notes.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(int numberOfPage, int numberOfSize){
        return userRepository.getAll(
                PageableConfig.pageable(numberOfPage,numberOfSize,"id")
        );
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user){
        return userRepository.findById(user.getId())
                .map(existUser -> {
                    if (user.getPassword() != null) {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    existUser.partialUpdate(user);
                    return existUser;
                })
                .orElseThrow(()-> new RuntimeException("User not found: " + user.getUsername()));
    }

    @Transactional
    public User deleteUser(Long id){
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                })
                .orElseThrow(()-> new RuntimeException("User not found: " + id));
    }

}
