package com.sentry.notes.exceptions;

import com.sentry.notes.exceptions.response.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandel extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> notFoundException(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ApiResponse(exception.getMessage(), HttpStatus.NOT_FOUND)
                );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> unauthorizedException(UnauthorizedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ApiResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED)
                );
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse> forbiddenException(ForbiddenException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        new ApiResponse(exception.getMessage(),HttpStatus.FORBIDDEN)
                );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse> alreadyExistsException(AlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        new ApiResponse(exception.getMessage(), HttpStatus.CONFLICT)
                );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse> invalidTokenException(InvalidTokenException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ApiResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED)
                );
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiResponse> tokenExpiredException(TokenExpiredException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ApiResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED)
                );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->  fieldError.getField()+": "+ fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ApiResponse(message,HttpStatus.BAD_REQUEST)
                );
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse> handelALlException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ApiResponse("Please communicate with Admin",
                                HttpStatus.INTERNAL_SERVER_ERROR)
                );
    }
}
