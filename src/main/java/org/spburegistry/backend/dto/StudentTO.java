package org.spburegistry.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTO {
    private String name;
    private String sex;
    private String degree;
    private String email;
    private String grade;
    private EducationalProgramTO educationalProgram;
}
