package com.example.schoolmanagementsoftware.ControllerAdviser;

import com.example.schoolmanagementsoftware.Api.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity MissingPathVariableException(MissingPathVariableException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = ObjectOptimisticLockingFailureException.class)
    public ResponseEntity ObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
}