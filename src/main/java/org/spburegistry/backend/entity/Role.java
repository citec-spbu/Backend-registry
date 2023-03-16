package org.spburegistry.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
@Data
public class Role extends BaseEntity {

    @Column(nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
