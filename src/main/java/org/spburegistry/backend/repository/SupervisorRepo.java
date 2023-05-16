package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepo extends JpaRepository<Supervisor, Long> {
    
}
