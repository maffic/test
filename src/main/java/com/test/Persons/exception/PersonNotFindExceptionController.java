package com.test.Persons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonNotFindExceptionController {
    @ExceptionHandler(value = PersonNotfoundException.class)
    public ResponseEntity<?> exception(PersonNotfoundException exception) {
        return new ResponseEntity<>(String.format("Person %d not found", exception.getId()), HttpStatus.NOT_FOUND);
    }
}
