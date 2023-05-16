package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleInProjectRepo extends JpaRepository<ProjectRole, Long> {

}
