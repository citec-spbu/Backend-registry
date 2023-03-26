package org.spburegistry.backend.service.implementation;

import java.util.Set;

import org.spburegistry.backend.ExceptionHandler.exception.NoSuchEntityException;
import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.repository.ProjectRepo;
import org.spburegistry.backend.service.ProjectService;
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
    public Iterable<Project> findAll() {
        return projectRepo.findAll();
    }

    @Override
    public Project findById(long id) {
        return projectRepo.findById(id).orElseThrow(
            () -> new NoSuchEntityException("Project with id " + id + " not found"));
        
    }
    

}
