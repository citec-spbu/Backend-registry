package org.spburegistry.backend.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.spburegistry.backend.dto.FacultyRequestTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.entity.Faculty;
import org.spburegistry.backend.repository.FacultyRepo;
import org.spburegistry.backend.service.FacultyService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepo facultyRepo;
    @Autowired
    public FacultyServiceImpl(FacultyRepo facultyRepo) {this.facultyRepo = facultyRepo;}

    @Override
    public FacultyTO findById(long id){
        Faculty faculty = facultyRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Faculty with id " + id + " not found")
        );
        return ConvertToTO.facultyToTO(faculty);
    }

    @Override
    public Iterable<FacultyTO> findAll() {
        return facultyRepo.findAll().stream()
                .map(ConvertToTO::facultyToTO)
                .collect(Collectors.toSet());
    }


    @Override
    public Faculty createFaculty(String name, String link) {
        Faculty newFaculty = Faculty.builder()
                .name(name)
                .link(link)
                .build();
        return facultyRepo.save(newFaculty);
    }

    @Override
    public FacultyTO addFaculty(FacultyRequestTO facultyRequestTO){
        Faculty newFaculty = createFaculty(facultyRequestTO.getName(), facultyRequestTO.getLink());
        return ConvertToTO.facultyToTO(newFaculty);
    }
}

