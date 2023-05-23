package org.spburegistry.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="links")
@Data
public class Link extends BaseEntity {
    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
