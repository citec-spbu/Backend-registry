package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
    
}
