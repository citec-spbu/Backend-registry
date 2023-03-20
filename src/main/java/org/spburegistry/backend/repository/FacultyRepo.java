package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {
    
}
