package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Curator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuratorRepo extends JpaRepository<Curator, Long> {
    
}
