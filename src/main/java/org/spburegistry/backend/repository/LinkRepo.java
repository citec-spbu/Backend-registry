package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepo extends JpaRepository<Link, Long> {
    
}
