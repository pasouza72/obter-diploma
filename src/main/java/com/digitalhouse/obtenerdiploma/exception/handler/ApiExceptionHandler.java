package com.digitalhouse.obtenerdiploma.exception.handler;

import com.digitalhouse.obtenerdiploma.exception.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validationError(MethodArgumentNotValidException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.toString());

        for (FieldError x : exception.getBindingResult().getFieldErrors()) {
            response.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
