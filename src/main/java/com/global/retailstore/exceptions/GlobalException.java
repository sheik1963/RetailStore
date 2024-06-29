package com.global.retailstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {




    @ExceptionHandler(value= InvaildUserException.class)
    ResponseEntity<String>  UserNotFoundException(InvaildUserException ex){

        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= CartIsEmptyException.class)
    ResponseEntity<String> productAlreadyPresentException(CartIsEmptyException ex){

        return  new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
