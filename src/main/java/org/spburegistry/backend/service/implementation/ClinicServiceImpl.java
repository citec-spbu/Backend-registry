package org.spburegistry.backend.service.implementation;

import java.util.Optional;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoEntityIdException;
import org.spburegistry.backend.dto.ClinicRequestTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.entity.Clinic;
import org.spburegistry.backend.entity.Faculty;
import org.spburegistry.backend.repository.ClinicRepo;
import org.spburegistry.backend.repository.FacultyRepo;
import org.spburegistry.backend.service.ClinicService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepo clinicRepo;
    private final FacultyRepo facultyRepo;

    @Autowired
    public ClinicServiceImpl(ClinicRepo clinicRepo, FacultyRepo facultyRepo) {
        this.clinicRepo = clinicRepo;
        this.facultyRepo = facultyRepo;
    }

    @Override
    public ClinicTO findById(long id) {
        return ConvertToTO.clinicToTO(clinicRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic with id " + id + " not found")));

    }

    @Override
    public ClinicTO findByName(String name) {
        return ConvertToTO.clinicToTO(Optional.ofNullable(clinicRepo.findByName(name))
                .orElseThrow(() -> new EntityNotFoundException("Clinic with name " + name + " not found")));
    }

    @Override
    public Iterable<ClinicTO> findAll() {
        return clinicRepo.findAll().stream().map(ConvertToTO::clinicToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public ClinicTO addClinic(ClinicRequestTO clinicRequestTO) {
        Clinic newClinic = Clinic.builder()
                .link(clinicRequestTO.getLink())
                .name(clinicRequestTO.getName())
                .faculty(getFaculty(clinicRequestTO))
                .build();
        Clinic clinic = clinicRepo.save(newClinic);
        return ConvertToTO.clinicToTO(clinic);
    }

    private Faculty getFaculty(ClinicRequestTO clinicRequestTO) {
        return facultyRepo.findById(
                Optional.ofNullable(clinicRequestTO.getFacultyId())
                        .orElseThrow(() -> new NoEntityIdException("Faculty Id is null")))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Faculty with id " + clinicRequestTO.getFacultyId() + " not found"));
    }

}