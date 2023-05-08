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
@Table(name="curators")
@Data
public class Curator extends BaseEntity {
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "curators")
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
