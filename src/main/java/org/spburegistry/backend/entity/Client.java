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
@Table(name="clients")
@Data
public class Client extends BaseEntity {
    @Column(name = "org_name", nullable = false)
    private String organizationName;

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "client")
    private Set<Project> projects = new HashSet<>();

    public void addProject(Project project) {
        projects.add(project);
    }

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Nullable
    private String phone;

    @Nullable
    private String link;
}
