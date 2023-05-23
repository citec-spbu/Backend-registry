package org.spburegistry.backend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import org.spburegistry.backend.enums.Status;
import org.spburegistry.backend.enums.WorkFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_student", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_clinic", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private Set<Clinic> clinics = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_client", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> clients = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_curator", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "curator_id"))
    private Set<Curator> curators = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_supervisor", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "supervisor_id"))
    private Set<Supervisor> supervisors = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_project", joinColumns = @JoinColumn(name = "first_project_id"), inverseJoinColumns = @JoinColumn(name = "second_project_id"))
    private Set<Project> linkedProjects = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "project_tag", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
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

    private <T> void compareAndSet(Supplier<T> getter, Consumer<T> setter, T value) {
        if (!Objects.equals(getter.get(), value)) {
            setter.accept(value);
        }
    }

    @PreRemove
    private void preRemove() {
        this.clients.clear();
        this.clinics.clear();
        this.curators.clear();
        this.students.clear();
        this.supervisors.clear();
        this.tags.clear();
        this.linkedProjects.forEach(linkedProject -> linkedProject.getLinkedProjects().remove(this));
        this.projectRoles.forEach(projectRole -> projectRole.setProject(null));
        this.links.forEach(link -> link.setProject(null));
        this.commits.forEach(commit -> commit.setProject(null));

    }

    public void compareAndSetName(String name) {
        compareAndSet(this::getName, this::setName, name);
    }

    public void compareAndSetDescription(String description) {
        compareAndSet(this::getDescription, this::setDescription, description);
    }

    public void compareAndSetRequirements(String requirements) {
        compareAndSet(this::getRequirements, this::setRequirements, requirements);
    }

    public void compareAndSetRequirementsForPerformers(String requirementsForPerformers) {
        compareAndSet(this::getRequirementsForPerformers, this::setRequirementsForPerformers,
                requirementsForPerformers);
    }

    public void compareAndSetStartTime(Date startTime) {
        compareAndSet(this::getStartTime, this::setStartTime, startTime);
    }

    public void compareAndSetStartFiling(Date startFiling) {
        compareAndSet(this::getStartFiling, this::setStartFiling, startFiling);
    }

    public void compareAndSetEndFiling(Date endFiling) {
        compareAndSet(this::getEndFiling, this::setEndFiling, endFiling);
    }

    public void compareAndSetStartImplementation(Date startImplementation) {
        compareAndSet(this::getStartImplementation, this::setStartImplementation, startImplementation);
    }

    public void compareAndSetEndImplementation(Date endImplementation) {
        compareAndSet(this::getEndImplementation, this::setEndImplementation, endImplementation);
    }

    public void compareAndSetStartDefense(Date startDefense) {
        compareAndSet(this::getStartDefense, this::setStartDefense, startDefense);
    }

    public void compareAndSetEndDefense(Date endDefense) {
        compareAndSet(this::getEndDefense, this::setEndDefense, endDefense);
    }

    public void compareAndSetStatus(Status status) {
        compareAndSet(this::getStatus, this::setStatus, status);
    }

    public void compareAndSetWorkFormat(WorkFormat workFormat) {
        compareAndSet(this::getWorkFormat, this::setWorkFormat, workFormat);
    }

    public void compareAndSetMaxStudents(int maxStudents) {
        compareAndSet(this::getMaxStudents, this::setMaxStudents, maxStudents);
    }

}
