package com.sentry.notes.aop;

import com.sentry.notes.annotation.RequireRole;
import com.sentry.notes.base.AppPointCuts;
import com.sentry.notes.entities.Note;
import com.sentry.notes.entities.User;
import com.sentry.notes.exceptions.ForbiddenException;
import com.sentry.notes.exceptions.UnauthorizedException;
import com.sentry.notes.security.userdetails.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(1)
@AllArgsConstructor
public class SecurityAspect extends AppPointCuts {

    private final SecurityContextHolder contextHolder;

    @Around("requireOwnership()")
    public Object checkOwnership(ProceedingJoinPoint joinPoint) throws Throwable{
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        Long requestedUserId = extractUserId(joinPoint.getArgs());
        if (!currentUser.userId().equals(requestedUserId)){
            log.warn("Unauthorized access attempt | user: {} tried to access: {}",
                    currentUser.userId(), requestedUserId);
            throw new UnauthorizedException("Access denied");
        }
        return joinPoint.proceed();
    }

    @Around("requireRole()")
    public Object checkRole(ProceedingJoinPoint joinPoint, RequireRole requireRole) throws Throwable{
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        boolean allowed = Arrays.asList(requireRole.role()).contains(currentUser.role());

        if (!allowed){
            log.warn("Forbidden | user: {} | required: {} | has: {}",
                    currentUser.userId(), requireRole.role(), currentUser.role());
            throw new ForbiddenException("You don't have permission");
        }
        return joinPoint.proceed();
    }

    private Long extractUserId(Object[] args){
        for (Object arg : args) {
            if (arg instanceof Long id) return id;
            if (arg instanceof User user) return user.getId();
            if (arg instanceof Note note) return note.getUser().getId();
        }
        throw new UnauthorizedException("Cannot verify ownership");
    }
}
