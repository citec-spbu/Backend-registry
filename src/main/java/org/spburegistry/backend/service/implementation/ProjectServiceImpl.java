package org.spburegistry.backend.service.implementation;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoSuchEntityException;
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
    public Set<ProjectTO> findAll() {
        return projectRepo.findAll().stream().map(project -> ConvertToTO.projectToTO(project))
                .collect(Collectors.toSet());
    }

    @Override
    public ProjectTO findById(long id) {
        Project project = projectRepo.findById(id).orElseThrow(
                () -> new NoSuchEntityException("Project with id " + id + " not found"));
        return ConvertToTO.projectToTO(project);

    }

    @Override
    public ProjectTO addProject(ProjectTO projectTO) {
        Project newProject = Project.builder()
                .description(projectTO.getDescription())
                .end(projectTO.getEnd())
                .maxStudents(projectTO.getMaxStudents())
                .name(projectTO.getName())
                .requirements(projectTO.getRequirements())
                .resultLink(projectTO.getResultLink())
                .scientificSupervisor(projectTO.getScientificSupervisor())
                .start(projectTO.getStart())
                .workFormat(projectTO.getWorkFormat())
                .build();
        newProject.setCreationTime(new Date());
        newProject.setLastUpdateTime(new Date());
        Client client = clientRepo.getReferenceById(projectTO.getClient().getClientId());
        newProject.setClient(client);
        Set<Clinic> clinics = projectTO.getClinics().stream()
                .map(clinicTO -> clinicRepo.getReferenceById(clinicTO.getClinicId()))
                .collect(Collectors.toSet());
        newProject.setClinics(clinics);
        Set<Tag> tags = projectTO.getTags().stream()
                .map(name -> {
                    Tag tag = tagRepo.findByName(name);
                    if (tag == null) {
                        tag = Tag.builder()
                                .name(name)
                                .build();
                        tag.setCreationTime(new Date());
                        tag.setLastUpdateTime(new Date());
                        tagRepo.save(tag);
                    }
                    return tag;
                }).collect(Collectors.toSet());
        newProject.setTags(tags);
        client.addProject(newProject);
        clinics.forEach(clinic -> clinic.addProject(newProject));
        tags.forEach(tag -> tag.addProject(newProject));
        Project project = projectRepo.save(newProject);
        return ConvertToTO.projectToTO(project);
    }

}
