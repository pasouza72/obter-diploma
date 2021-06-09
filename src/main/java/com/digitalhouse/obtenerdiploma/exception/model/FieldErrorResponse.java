package com.digitalhouse.obtenerdiploma.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldErrorResponse {
    private String fieldName;
    private String message;
}
