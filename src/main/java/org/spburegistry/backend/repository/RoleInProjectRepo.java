package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.RoleInProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleInProjectRepo extends JpaRepository<RoleInProject, Long> {
    
}
