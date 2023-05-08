package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.FacultyRequestTO;
import org.spburegistry.backend.dto.FacultyTO;

public interface FacultyService {
    Iterable<FacultyTO> findAll();

    FacultyTO findById(long id);

    FacultyTO addFaculty(FacultyRequestTO facultyRequestTO);
}
