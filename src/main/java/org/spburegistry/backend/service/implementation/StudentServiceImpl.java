package org.spburegistry.backend.service.implementation;

import java.util.Optional;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoEntityIdException;
import org.spburegistry.backend.dto.EducationalProgramTO;
import org.spburegistry.backend.dto.StudentTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.EducationalProgram;
import org.spburegistry.backend.entity.Student;
import org.spburegistry.backend.repository.EducationalProgramRepo;
import org.spburegistry.backend.repository.StudentRepo;
import org.spburegistry.backend.service.StudentService;
import org.spburegistry.backend.service.UserService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final UserService userService;
    private final EducationalProgramRepo educationalProgramRepo;

    @Autowired
    public StudentServiceImpl(EducationalProgramRepo educationalProgramRepo, StudentRepo studentRepo,
            UserService userService) {
        this.studentRepo = studentRepo;
        this.userService = userService;
        this.educationalProgramRepo = educationalProgramRepo;
    }

    @Override
    public Iterable<UserTO> findAll() {
        return studentRepo.findAll().stream()
                .map(Student::getUser)
                .map(ConvertToTO::userToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserTO findById(Long id) {
        return studentRepo.findById(id)
                .map(Student::getUser)
                .map(ConvertToTO::userToTO)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));
    }

    @Override
    public StudentTO addStudent(StudentTO userTO) {
        return ConvertToTO.studentToTO(
                studentRepo.save(
                        Student.builder()
                                .sex(userTO.getSex())
                                .degree(userTO.getDegree())
                                .grade(userTO.getGrade())
                                .educationalProgram(getEducationalProgram(userTO.getEducationalProgram()))
                                .user(userService.createUser(
                                        userTO.getName(),
                                        userTO.getEmail()))
                                .build()));
    }

    private EducationalProgram getEducationalProgram(EducationalProgramTO educationalProgramTO) {
        return educationalProgramRepo.findById(
                Optional.ofNullable(educationalProgramTO.getEducationalProgramId())
                        .orElseThrow(() -> new NoEntityIdException("Faculty Id is null")))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Faculty with id " + educationalProgramTO.getEducationalProgramId() + " not found"));
    }
}
