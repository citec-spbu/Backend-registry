package org.spburegistry.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="requirements")
@Data
public class Requirement extends BaseEntity{
    private String requirement;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
