package com.sentry.notes.aop;

import com.sentry.notes.base.AppPointCuts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(3)
public class PerformanceAspect extends AppPointCuts {

    private static final long SLOW_METHOD_THRESHOLD = 1000L;

    @Around("noteService() || userService()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return perform(joinPoint);
    }

    @Around("noteController() || userController()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable{
        return perform(joinPoint);
    }

    @Around("noteAppService() || userAppService()")
    public Object logAppService(ProceedingJoinPoint joinPoint) throws Throwable{
        return perform(joinPoint);
    }

    @Around("publicController()")
    public Object logPublicController(ProceedingJoinPoint joinPoint) throws Throwable{
        return perform(joinPoint);
    }

    private Object perform(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

            if (executionTime > SLOW_METHOD_THRESHOLD) {
                log.warn("Slow method: {} took {}ms", methodName, executionTime);
            }

            log.info("Success: {} | time: {}ms", methodName, executionTime);
            return result;

        } catch (Throwable e) {
            long executionTime = System.currentTimeMillis() - startTime;
            log.error("Failed: {} | time: {}ms | error: {}", methodName, executionTime, e.getMessage());
            throw e;
        }
    }
}
