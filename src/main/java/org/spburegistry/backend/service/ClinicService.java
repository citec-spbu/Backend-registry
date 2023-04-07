package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ClinicRequestTO;
import org.spburegistry.backend.dto.ClinicTO;

public interface ClinicService {
    ClinicTO findById(long id);
    ClinicTO findByName(String name);
    Iterable<ClinicTO> findAll();
    ClinicTO addClinic(ClinicRequestTO clinicRequestTO);
}