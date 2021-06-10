package com.digitalhouse.obtenerdiploma.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SubjectDTO {

  @NotNull(message = "subject cannot be null")
  @Pattern(regexp = "(^[\\p{L}\\s]{8,50})",
            message = "the subject field must contain only letters and be between 8 and 50 characters")
  private String subject;

  @NotNull(message = "note cannot be null")
  @Range(min = 0, max = 10, message = "the nota field must be a integer number between 0 and 10")
  private Integer note;


}
