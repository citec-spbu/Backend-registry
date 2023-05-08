package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="supervisors")
@Data
public class Supervisor extends BaseEntity {
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "supervisors")
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project) {
        projects.add(project);
    }

    @OneToOne
    private User user;

    @Nullable
    private String phone;

    @Nullable
    private String link;
}
