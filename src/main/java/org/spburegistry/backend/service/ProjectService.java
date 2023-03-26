package org.spburegistry.backend.service;

import org.spburegistry.backend.entity.Project;

public interface ProjectService {
    public Iterable<Project> findAll();
    public Project findById(long id);
}
