package com.digitalhouse.obtenerdiploma.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CertificateDTO extends StudentDTO {
  private String message;
  private Double average;
  private StudentDTO student;


  public CertificateDTO(StudentDTO student) {
    this.student = student;
  }


}
