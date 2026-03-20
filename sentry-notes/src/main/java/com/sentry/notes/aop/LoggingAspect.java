package com.sentry.notes.aop;

import com.sentry.notes.base.AppPointCuts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect extends AppPointCuts {

    @Around("noteService() || userService()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logging(joinPoint);
    }

    @Around("noteController() || userController()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable{
        return logging(joinPoint);
    }

    @Around("noteAppService() || userAppService()")
    public Object logAppService(ProceedingJoinPoint joinPoint) throws Throwable{
        return logging(joinPoint);
    }

    @Around("publicController()")
    public Object logPublicController(ProceedingJoinPoint joinPoint) throws Throwable{
        return logging(joinPoint);
    }

    private Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        log.info("Calling method: {} with arguments: {}", methodName, arguments);

        try {
            Object result = joinPoint.proceed();
            log.info("Success: {} returned: {}", methodName, result);
            return result;
        } catch (Throwable e) {
            log.error("Failed: {} | error: {}", methodName, e.getMessage());
            throw e;
        }
    }

}
