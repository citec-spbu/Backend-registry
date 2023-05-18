package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ProjectTO;

import java.util.Optional;

public interface ProjectService {
    Iterable<ProjectTO> findAll();
    ProjectTO findById(long id);

    ProjectTO addProject(ProjectTO project);

    Iterable<ProjectTO> findRandomProjects(Optional<Long> limit);
}
