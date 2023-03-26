package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.EducationalProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationalProgramRepo extends JpaRepository<EducationalProgram, Long> {
    
}
