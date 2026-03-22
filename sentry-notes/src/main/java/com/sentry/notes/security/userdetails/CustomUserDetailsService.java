package com.sentry.notes.security.userdetails;

import com.sentry.notes.entities.User;
import com.sentry.notes.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);

        return new CustomUserDetails(
                user.getId(),
                user.getRole(),
                user.getUsername(),
                user.getPassword(),
                grantedAuthority(user)
        );
    }

    private Set<GrantedAuthority> grantedAuthority(User user) {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }
}
