package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepo extends JpaRepository<Requirement, Long>{
    
}
