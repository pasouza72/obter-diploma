package com.digitalhouse.obtenerdiploma.exception.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ExceptionResponse {

    private String code;
    private List<FieldErrorResponse> errors;

    public ExceptionResponse(String code) {
        this.code = code;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldErrorResponse(fieldName, message));
    }



}
