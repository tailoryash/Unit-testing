package com.simform.hibernate1.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> exception() {
        return new ResponseEntity<>("Please enter valid AccountNo", HttpStatus.NOT_FOUND);
    }
}
