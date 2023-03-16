package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "client")
    private Set<Project> projects;

    @OneToOne(mappedBy = "client")
    @Nullable
    private User user;

    @Nullable
    private String name;

    @Nullable
    private String email;

    @Nullable
    private String phone;

    @Nullable
    private String link;
}
