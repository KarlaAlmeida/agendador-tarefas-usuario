package com.javanauta.agendador_tarefas_usuario.controller;

import com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions.ConflictExceptions;
import com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendador_tarefas_usuario.infraestructure.exceptions.UnauthorazedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictExceptions.class)
    public ResponseEntity<String> handleConflictException(ConflictExceptions exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorazedException.class)
    public ResponseEntity<String> handleUnauthorazedException(UnauthorazedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }


}
