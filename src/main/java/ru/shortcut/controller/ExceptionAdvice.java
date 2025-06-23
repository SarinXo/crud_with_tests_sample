package ru.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.shortcut.exceptions.TaskNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Void> handleTaskNofFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
