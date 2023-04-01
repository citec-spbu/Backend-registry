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
@Table(name="clinics")
@Data
public class Clinic extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "clinics")
    private Set<Project> projects = new HashSet<>();

    @Nullable
    private String link;
}
