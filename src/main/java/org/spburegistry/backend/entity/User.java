package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.spburegistry.backend.enums.Role;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Data
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Nullable
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    @Nullable
    private Student student;

    @OneToOne(mappedBy = "user")
    @Nullable
    private Client client;
}