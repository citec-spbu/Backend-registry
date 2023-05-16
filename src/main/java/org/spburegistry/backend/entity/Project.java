package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import org.spburegistry.backend.enums.Status;
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

        @Column(columnDefinition = "text")
        private String requirementsForPerformers;

        @Column(name = "work_format", nullable = false)
        @Enumerated(EnumType.STRING)
        private WorkFormat workFormat;
        
        @Column(name = "start_time")
        private Date startTime;

        @Column(name = "start_filing")
        private Date startFiling;

        @Column(name = "end_filing")
        private Date endFiling;

        @Column(name = "start_implementation")
        private Date startImplementation;

        @Column(name = "end_implementation")
        private Date endImplementation;

        @Column(name = "start_defense")
        private Date startDefense;

        @Column(name = "end_defense")
        private Date endDefense;
        
        @Column(name = "max_students")
        private int maxStudents;

        @Column(name = "status", nullable = false)
        @Enumerated(EnumType.STRING)
        private Status status;

        @Column(name = "result_link")
        @Nullable
        private String resultLink;

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_student", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "student_id")
        )
        private Set<Student> students = new HashSet<>();

        public void addStudent(Student student) {
                students.add(student);
        }

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_clinic", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "clinic_id")
        )
        private Set<Clinic> clinics = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_client", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "client_id")
        )
        private Set<Client> clients = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_curator", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "curator_id")
        )
        private Set<Curator> curators = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_supervisor", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "supervisor_id")
        )
        private Set<Supervisor> supervisors = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_project", 
                joinColumns = @JoinColumn(name = "first_project_id"), 
                inverseJoinColumns = @JoinColumn(name = "second_project_id")
        )
        private Set<Project> linkedProjects = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "project_tag", 
                joinColumns = @JoinColumn(name = "project_id"), 
                inverseJoinColumns = @JoinColumn(name = "tag_id")
        )
        private Set<Tag> tags = new HashSet<>();


        @Builder.Default
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
        private Set<Commit> commits = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
        private Set<Link> links = new HashSet<>();

        @EqualsAndHashCode.Exclude
        @Builder.Default
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
        private Set<ProjectRole> projectRoles = new HashSet<>();
}
