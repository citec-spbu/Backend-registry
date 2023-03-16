package org.spburegistry.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="commits")
@Data
public class Commit extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "commit_date", nullable = false)
    private Date commitDate;

    @Column(name = "num_differences", nullable = false)
    private int numDifferences;
}
