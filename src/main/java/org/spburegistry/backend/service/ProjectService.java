package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ProjectTO;

public interface ProjectService {
    Iterable<ProjectTO> findAll();
    ProjectTO findById(long id);
    ProjectTO addProject(ProjectTO project);
}
