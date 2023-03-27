package org.spburegistry.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.spburegistry.backend.enums.Degree;
import org.spburegistry.backend.enums.Sex;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="students")
@Data
public class Student extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column(nullable = false)
    private int grade;

    @ManyToOne
    @JoinColumn(name = "educational_program_id", nullable = false)
    private EducationalProgram educationalProgram;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder.Default
    @ManyToMany(mappedBy = "students")
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project) {
        projects.add(project);
    }

    @Builder.Default
    @OneToMany(mappedBy = "student")
    private Set<Commit> commits = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "student")
    private Set<ProjectRole> roles = new HashSet<>();
}
