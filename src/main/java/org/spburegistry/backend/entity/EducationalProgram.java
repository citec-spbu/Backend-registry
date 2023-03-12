package org.spburegistry.backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="educational_programs")
@Data
public class EducationalProgram extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "educationalProgram")
    private Set<Project> projects;

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable = false)
    private Faculty faculty;

    @Column(nullable = false)
    private String code;
}
