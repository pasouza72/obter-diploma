package com.digitalhouse.obtenerdiploma.service;

import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;
import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CertificateServiceImplTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final CertificateServiceImpl service = new CertificateServiceImpl();


    @Test
    public void shouldValidDiplomaWhenAverageIsLessThanNine(){
        StudentDTO studentDTO = getJsonFromFile();
        String messageExpected = studentDTO.getName() + " usted ha conseguido el promedio de " + 7.666666666666667;

        CertificateDTO certificateDTO = service.analyzeNotes(studentDTO);

        assertEquals(messageExpected, certificateDTO.getMessage());
    }

    @Test
    public void shouldValidHonorsWhenAverageIsLessThanNine(){
        StudentDTO studentDTO = getJsonFromFile();
        String messageExpected = "¡Felicitaciones " + studentDTO.getName() + "! Usted tiene el gran promedio de " + 10.0;

        CertificateDTO certificateDTO = service.analyzeNotes(studentDTO);

        assertNotEquals(messageExpected, certificateDTO.getMessage());
    }

    @Test
    public void shouldValidDiplomaWhenAverageIsMoreThanNine(){
        StudentDTO studentDTO = getJsonFromFile();
        studentDTO.getSubjects().forEach(a -> a.setNote(10));
        String messageExpected = "¡Felicitaciones " + studentDTO.getName() + "! Usted tiene el gran promedio de " + 10.0;

        CertificateDTO certificateDTO = service.analyzeNotes(studentDTO);

        assertEquals(messageExpected, certificateDTO.getMessage());
    }

    @Test
    public void shouldValidAverageNotEquals(){
        StudentDTO studentDTO = getJsonFromFile();
        Double sumOfNotes = 23.0;

        CertificateDTO certificateDTO = service.analyzeNotes(studentDTO);

        assertNotEquals(sumOfNotes, certificateDTO.getAverage());
    }

    @Test
    public void shouldValidAverageEquals(){
        StudentDTO studentDTO = getJsonFromFile();

        CertificateDTO certificateDTO = service.analyzeNotes(studentDTO);

        assertEquals(7.666666666666667, certificateDTO.getAverage());
    }

    @Test
    public void shouldReturnOkWhenNameIsNotEmpty(){
        StudentDTO studentDTO = getJsonFromFile();

        CertificateDTO certificateDTO = service.analyzeNotes(studentDTO);

        assertEquals("Kamilaaaa", certificateDTO.getStudent().getName());
    }

    @Test
    public void shouldReturnExceptionWhenNameIsEmpty(){
        StudentDTO studentDTO = getJsonFromFile();
        studentDTO.setName("");

        assertThrows(ConstraintViolationException.class, () -> service.analyzeNotes(studentDTO) );
    }

    @Test
    public void shouldReturnExceptionWhenSubjectIsNull(){
        StudentDTO studentDTO = getJsonFromFile();
        studentDTO.setSubjects(null);

        assertThrows(ConstraintViolationException.class, () -> service.analyzeNotes(studentDTO) );
    }

    private StudentDTO getJsonFromFile() {
        try {
            return mapper.readValue(new File("src/test/resources/Payload.json"), StudentDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Cannot Found file");
        }
    }
}