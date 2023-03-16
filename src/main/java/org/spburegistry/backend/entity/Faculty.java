package org.spburegistry.backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="faculties")
@Data
public class Faculty extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Set<EducationalProgram> educationalPrograms;

    @OneToMany(mappedBy = "faculty")
    private Set<Clinic> clinics;

    @Column(nullable = false)
    private String link;
}
