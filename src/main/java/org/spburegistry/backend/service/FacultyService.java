//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.entity.Faculty;

public interface FacultyService {
    Iterable<FacultyTO> findAll();

    FacultyTO findById(long id);
    Faculty createFaculty(String name, String link);
}
