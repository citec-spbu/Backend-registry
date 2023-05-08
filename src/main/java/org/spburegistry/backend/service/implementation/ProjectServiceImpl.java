package org.spburegistry.backend.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.spburegistry.backend.ExceptionHandler.exception.NoEntityIdException;
import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.Clinic;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.entity.Tag;
import org.spburegistry.backend.repository.ClientRepo;
import org.spburegistry.backend.repository.ClinicRepo;
import org.spburegistry.backend.repository.ProjectRepo;
import org.spburegistry.backend.repository.TagRepo;
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

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo, ClientRepo clientRepo, ClinicRepo clinicRepo, TagRepo tagRepo) {
        this.projectRepo = projectRepo;
        this.clientRepo = clientRepo;
        this.clinicRepo = clinicRepo;
        this.tagRepo = tagRepo;
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

    // TODO: with the fields that are in the ProjectTO change this function
    // TODO: you need make function getCurators, getSupervisors, getLinks, getLinkedProjects
    @Override
    public ProjectTO addProject(ProjectRequestTO projectRequest) {
        Project newProject = Project.builder()
                .description(projectRequest.getDescription())
                .maxStudents(projectRequest.getMaxStudents())
                .name(projectRequest.getName())
                .requirements(projectRequest.getRequirements())
                .workFormat(projectRequest.getWorkFormat())
                .clinics(getClinics(projectRequest.getClinicsIds()))
                .tags(getTags(projectRequest.getTags()))
                .build();
        Project project = projectRepo.save(newProject);
        return ConvertToTO.projectToTO(project);
    }

    private Set<Tag> getTags(Set<TagTO> tags) {
        return Optional.ofNullable(tags)
                .map(tagTOS ->
                        tagTOS.stream().map(tag -> {
                            if (tag.getTagId() == null) {
                                Tag newTag = tagRepo.findByNameIgnoreCase(tag.getName());
                                if (newTag == null) {
                                    newTag = Tag.builder()
                                            .name(tag.getName())
                                            .build();
                                    tagRepo.save(newTag);
                                }
                                return newTag;
                            }
                            return tagRepo.getReferenceById(tag.getTagId());
                        }).collect(Collectors.toSet()))
                .orElse(new HashSet<Tag>());
    }

    private Set<Clinic> getClinics(Set<Long> clinicsIds) {
        return Optional.ofNullable(clinicsIds)
                .map(clinics ->
                        clinics.stream()
                                .map(clinicRepo::getReferenceById)
                                .collect(Collectors.toSet()))
                .orElse(new HashSet<Clinic>());
    }

    private Set<Client> getClients(Set<Long> clientIds) {
        return Optional.ofNullable(clientIds)
                .map(clients ->
                        clients.stream()
                                .map(clientRepo::getReferenceById)
                                .collect(Collectors.toSet()))
                .orElse(new HashSet<Client>());
    }

}
