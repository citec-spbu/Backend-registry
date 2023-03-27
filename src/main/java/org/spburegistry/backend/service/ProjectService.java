package org.spburegistry.backend.service;

import java.util.Set;

import org.spburegistry.backend.dto.ProjectTO;

public interface ProjectService {
    public Set<ProjectTO> findAll();
    public ProjectTO findById(long id);
    public ProjectTO addProject(ProjectTO project);
}
