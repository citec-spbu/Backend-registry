package org.spburegistry.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles_in_projects")
@Data
public class ProjectRole extends BaseEntity {

    @Column(nullable = false)
    private String role;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
