package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.spburegistry.backend.enums.WorkFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
@Data
public class Project extends BaseEntity {

        @Column(nullable = false)
        private String name;

        @Column(nullable = false, columnDefinition = "text")
        private String description;

        @Column(columnDefinition = "text")
        private String requirements;

        @Column(name = "work_format", nullable = false)
        @Enumerated(EnumType.STRING)
        private WorkFormat workFormat;
        
        @Column(name = "start_time")
        private Date start;
        
        @Column(name = "end_time")
        private Date end;
        
        @Column(name = "max_students")
        private int maxStudents;
        
        @Column(name = "scientific_supervisor")
        @Nullable
        private String scientificSupervisor;

        @Column(name = "result_link")
        @Nullable
        private String resultLink;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "client_id", nullable = false)
        private Client client;

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(
                name = "project_student", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "student_id")
        )
        private Set<Student> students = new HashSet<>();

        public void addStudent(Student student) {
                students.add(student);
                student.addProject(this);
        }

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable(
                name = "project_clinic", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "clinic_id")
        )
        private Set<Clinic> clinics = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable(
                name = "project_tag", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private Set<Tag> tags = new HashSet<>();


        @Builder.Default
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
        private Set<Commit> commits = new HashSet<>();

        @Builder.Default
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
        private Set<Link> links = new HashSet<>();

        @Builder.Default
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
        private Set<Requirement> requirementsForPerformers = new HashSet<>();

}
