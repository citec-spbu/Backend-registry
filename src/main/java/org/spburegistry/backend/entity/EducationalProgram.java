package org.spburegistry.backend.entity;


import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable = false)
    private Faculty faculty;

    @Column(nullable = false)
    private String code;
}
