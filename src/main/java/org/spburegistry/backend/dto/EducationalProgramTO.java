package org.spburegistry.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalProgramTO {
    private Long educationalProgramId;
    private String code;
    private String name;
    private FacultyTO faculty;
}
