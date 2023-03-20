package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    
}
