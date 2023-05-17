package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;

public interface ProjectService {
    Iterable<ProjectTO> findAll();
    ProjectTO findById(long id);
    ProjectTO addProject(ProjectRequestTO project);
}
