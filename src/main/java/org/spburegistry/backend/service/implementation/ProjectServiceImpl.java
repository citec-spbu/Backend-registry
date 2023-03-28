package org.spburegistry.backend.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoClientIdException;
import org.spburegistry.backend.dto.ProjectRequest;
import org.spburegistry.backend.dto.ProjectTO;
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

import jakarta.persistence.EntityNotFoundException;

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
        return projectRepo.findAll().stream().map(project -> ConvertToTO.projectToTO(project))
                .collect(Collectors.toSet());
    }

    @Override
    public ProjectTO findById(long id) {
        Project project = projectRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project with id " + id + " not found"));
        return ConvertToTO.projectToTO(project);

    }

    @Override
    public ProjectTO addProject(ProjectRequest projectRequest) {
        Project newProject = Project.builder()
                .description(projectRequest.getDescription())
                .end(projectRequest.getEnd())
                .maxStudents(projectRequest.getMaxStudents())
                .name(projectRequest.getName())
                .requirements(projectRequest.getRequirements())
                .scientificSupervisor(projectRequest.getScientificSupervisor())
                .start(projectRequest.getStart())
                .workFormat(projectRequest.getWorkFormat())
                .build();

        if (projectRequest.getClientId() == null) {
            throw new NoClientIdException("Client Id is null");
        }
        Client client = clientRepo.getReferenceById(projectRequest.getClientId());
        if (client.getCreationTime() == null) {
            throw new EntityNotFoundException("Client with id " + projectRequest.getClientId() + " not found");
        }
        newProject.setClient(client);

        Set<Clinic> clinics = projectRequest.getClinicsId().stream()
                .map(clinicId -> clinicRepo.getReferenceById(clinicId))
                .collect(Collectors.toSet());
        newProject.setClinics(clinics);

        Set<Tag> tags = projectRequest.getTags().stream()
                .map(tag -> {
                    if (tag.getTagId() == null) {
                        Tag newTag = tagRepo.findByName(tag.getName());
                        if (newTag == null) {
                            newTag = Tag.builder()
                                    .name(tag.getName())
                                    .build();
                            tagRepo.save(newTag);
                        }
                        return newTag;
                    } else {
                        return tagRepo.getReferenceById(tag.getTagId());
                    }
                }).collect(Collectors.toSet());
        newProject.setTags(tags);
        
        Project project = projectRepo.save(newProject);
        return ConvertToTO.projectToTO(project);
    }

}
