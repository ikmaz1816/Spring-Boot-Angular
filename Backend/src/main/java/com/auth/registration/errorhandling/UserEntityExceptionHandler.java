package com.auth.registration.errorhandling;

import com.auth.registration.customexception.UserNotFoundException;
import com.auth.registration.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class UserEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> getException(WebRequest req, UserNotFoundException userNotFoundException)
    {
        ErrorResponse errorResponse=ErrorResponse.builder().message(userNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
