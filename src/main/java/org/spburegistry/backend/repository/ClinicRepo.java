package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepo extends JpaRepository<Clinic, Long> {

    public Clinic findByName(String name);
    
}
