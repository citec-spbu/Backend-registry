package org.spburegistry.backend.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.spburegistry.backend.dto.LinkTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.dto.RoleTO;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.entity.*;
import org.spburegistry.backend.repository.*;
import org.spburegistry.backend.service.ProjectService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final ClientRepo clientRepo;
    private final ClinicRepo clinicRepo;
    private final TagRepo tagRepo;
    private final StudentRepo studentRepo;
    private final CuratorRepo curatorRepo;
    private final SupervisorRepo supervisorRepo;
    private final LinkRepo linkRepo;
    private final RoleInProjectRepo projectRoleRepo;

    @Autowired
    public ProjectServiceImpl(
            RoleInProjectRepo projectRoleRepo,
            SupervisorRepo supervisorRepo,
            CuratorRepo curatorRepo,
            StudentRepo studentRepo,
            ProjectRepo projectRepo,
            ClientRepo clientRepo,
            ClinicRepo clinicRepo,
            TagRepo tagRepo,
            LinkRepo linkRepo) {
        this.projectRepo = projectRepo;
        this.clientRepo = clientRepo;
        this.clinicRepo = clinicRepo;
        this.tagRepo = tagRepo;
        this.studentRepo = studentRepo;
        this.curatorRepo = curatorRepo;
        this.supervisorRepo = supervisorRepo;
        this.linkRepo = linkRepo;
        this.projectRoleRepo = projectRoleRepo;
    }

    @Override
    public Iterable<ProjectTO> findAll() {
        return projectRepo.findAll().stream().map(ConvertToTO::projectToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public ProjectTO findById(long id) {
        Project project = projectRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project with id " + id + " not found"));
        return ConvertToTO.projectToTO(project);

    }

    @Override
    public ProjectTO addProject(ProjectTO projectRequest) {
        Project newProject = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .requirements(projectRequest.getRequirements())
                .requirementsForPerformers(projectRequest.getRequirementsForPerformers())
                .workFormat(projectRequest.getWorkFormat())
                .startTime(projectRequest.getStartTime())
                .startFiling(projectRequest.getStartFiling())
                .endFiling(projectRequest.getEndFiling())
                .startImplementation(projectRequest.getStartImplementation())
                .endImplementation(projectRequest.getEndImplementation())
                .startDefense(projectRequest.getStartDefense())
                .maxStudents(projectRequest.getMaxStudents())
                .status(projectRequest.getStatus())
                .students(getStudents(projectRequest.getProjectRoles()))
                .clinics(getClinics(projectRequest.getClinicsIds()))
                .clients(getClients(projectRequest.getClientsIds()))
                .curators(getCurators(projectRequest.getCuratorsIds()))
                .supervisors(getSupervisors(projectRequest.getSupervisorIds()))
                .linkedProjects(getLinkedProjects(projectRequest.getLinkedProjectsIds()))
                .tags(getTags(projectRequest.getTags()))
                .build();
        Project project = projectRepo.save(newProject);
        project.setLinks(saveLinksInProject(projectRequest.getLinks(), project));
        project.setProjectRoles(saveRolesInProject(projectRequest.getProjectRoles(), project));
        return ConvertToTO.projectToTO(projectRepo.save(project));
    }

    private Set<Tag> getTags(Set<TagTO> tags) {
        return Optional.ofNullable(tags)
                .map(tagTOS -> tagTOS.stream()
                        .map(this::handleTagTO)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Tag handleTagTO(TagTO tag) {
        if (tag.getTagId() != null) {
            return tagRepo.getReferenceById(tag.getTagId());
        }

        Tag newTag = tagRepo.findByNameIgnoreCase(tag.getName());
        if (newTag != null) {
            return newTag;
        }

        return tagRepo.save(Tag.builder()
                .name(tag.getName())
                .build());
    }

    private Set<Clinic> getClinics(Set<Long> clinicsIds) {
        return Optional.ofNullable(clinicsIds)
                .map(clinics -> clinics.stream()
                        .map(clinicRepo::getReferenceById)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Set<Client> getClients(Set<Long> clientIds) {
        return Optional.ofNullable(clientIds)
                .map(clients -> clients.stream()
                        .map(clientRepo::getReferenceById)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Set<Curator> getCurators(Set<Long> CuratorIds) {
        return Optional.ofNullable(CuratorIds)
                .map(curators -> curators.stream()
                        .map(curatorRepo::getReferenceById)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Set<Supervisor> getSupervisors(Set<Long> supervisorIds) {
        return Optional.ofNullable(supervisorIds)
                .map(supervisors -> supervisors.stream()
                        .map(supervisorRepo::getReferenceById)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Set<Student> getStudents(Set<RoleTO> projectRolesTO) {
        return Optional.ofNullable(projectRolesTO)
                .map(roles -> roles.stream()
                        .map(role -> studentRepo.getReferenceById(role.getStudentId()))
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Set<Project> getLinkedProjects(Set<Long> linkedProjectsIds) {
        return Optional.ofNullable(linkedProjectsIds)
                .map(linkedProjects -> linkedProjects.stream()
                        .map(projectRepo::getReferenceById)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Set<ProjectRole> saveRolesInProject(Set<RoleTO> projectRolesTO, Project project) {
        return Optional.ofNullable(projectRolesTO)
                .map(roles -> roles.stream()
                        .map(role -> createProjectRole(project, role))
                        .map(projectRoleRepo::save)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private ProjectRole createProjectRole(Project project, RoleTO role) {
        Student student = studentRepo.getReferenceById(role.getStudentId());
        return ProjectRole.builder()
                .project(project)
                .role(role.getRole())
                .student(student)
                .build();
    }

    private Set<Link> saveLinksInProject(Set<LinkTO> linksTO, Project project) {
        return Optional.ofNullable(linksTO)
                .map(links -> links.stream()
                        .map(link -> createLink(link, project))
                        .map(linkRepo::save)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    private Link createLink(LinkTO link, Project project) {
        return Link.builder()
                .project(project)
                .name(link.getName())
                .link(link.getLink())
                .build();
    }

}
