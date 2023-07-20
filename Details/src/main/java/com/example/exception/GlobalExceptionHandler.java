package com.example.exception;

import java.time.LocalDateTime;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> handleException(Exception ex,WebRequest req) {
        // Handle the exception and return an appropriate response or error message
    	MyErrorDetails err = new MyErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<MyErrorDetails> handleException(StudentException ex,WebRequest req) {
        // Handle the exception and return an appropriate response or error message
    	MyErrorDetails err = new MyErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
	public ResponseEntity<MyErrorDetails> myErrorNotFound(NotFoundException er, WebRequest req){
		MyErrorDetails err = new MyErrorDetails(er.getMessage(), req.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
}
