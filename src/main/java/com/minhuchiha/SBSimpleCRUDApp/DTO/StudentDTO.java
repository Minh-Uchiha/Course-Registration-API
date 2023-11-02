package com.minhuchiha.SBSimpleCRUDApp.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentDTO {
    private UUID studentId;
    private String name;
    private double gpa;
    private Date dob;
    private String email;
}
