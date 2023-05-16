package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepo extends JpaRepository<Link, Long> {

    Link findByLinkIgnoreCase(String link);
    Link findByProjectIdAndLink(Long projectId, String link);
    
}
