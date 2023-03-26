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

    @Builder.Default
    @OneToMany(mappedBy = "client")
    private Set<Project> projects = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Nullable
    private String phone;

    @Nullable
    private String link;
}
