package com.digitalhouse.obtenerdiploma.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDTO {

    @NotNull(message = "name cannot be null")
    @Pattern(regexp = "(^[A-Za-z\\s]{8,50})",
            message = "the name must be between 8 and 50 characters")
    private String name;

    @NotNull(message = "subjects cannot be null")
    @Valid
    private List<SubjectDTO> subjects;

}
