package org.spburegistry.backend.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoSuchEntityException;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.repository.ProjectRepo;
import org.spburegistry.backend.service.ProjectService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
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


}
