package org.spburegistry.backend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tags")
@Data
public class Tag extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Project> projects;
}
