package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    
}
