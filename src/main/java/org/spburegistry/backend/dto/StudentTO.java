package org.spburegistry.backend.dto;

import org.spburegistry.backend.enums.Degree;
import org.spburegistry.backend.enums.Sex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTO {
    private Long studentId;
    private String name;
    private Sex sex;
    private Degree degree;
    private String email;
    private int grade;
    private EducationalProgramTO educationalProgram;
}
