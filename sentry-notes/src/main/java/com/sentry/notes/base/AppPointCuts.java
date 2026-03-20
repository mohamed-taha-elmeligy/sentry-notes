package com.sentry.notes.base;

import org.aspectj.lang.annotation.Pointcut;

public abstract class AppPointCuts {
    // ===== Service point cut =====
    @Pointcut(value = "execution(*.com.sentry.notes.services.NoteService.*(..))")
    public void noteService(){}

    @Pointcut(value = "execution(*.com.sentry.notes.services.UserService.*(..))")
    public void userService(){}

    // ===== Controller point cut =====
    @Pointcut(value = "execution(*.com.sentry.notes.controllers.NoteController.*(..))")
    public void noteController(){}

    @Pointcut(value = "execution(*.com.sentry.notes.controllers.UserController.*(..)))")
    public void userController(){}

    // ===== AppService point cut =====
    @Pointcut(value = "execution(*.com.sentry.notes.appservice.NoteAppService.*(..))")
    public void noteAppService(){}

    @Pointcut(value = "execution(*.com.sentry.notes.appservice.UserAppService.*(..))")
    public void userAppService(){}

    // ===== Public Controller point cut =====
    @Pointcut(value = "execution(*.com.sentry.notes.controllers.PublicController.*(..))")
    public void publicController(){}
}
