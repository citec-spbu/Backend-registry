package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ProjectTO;

public interface ProjectService {
    public Iterable<ProjectTO> findAll();
    public ProjectTO findById(long id);
}
