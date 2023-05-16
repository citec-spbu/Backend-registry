package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.StudentTO;
import org.spburegistry.backend.dto.UserTO;

public interface StudentService {
    Iterable<UserTO> findAll();
    UserTO findById(Long id);
    StudentTO addStudent(StudentTO userTO);
}
