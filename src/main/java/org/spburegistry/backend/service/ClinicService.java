package org.spburegistry.backend.service;

import java.util.Optional;

import org.spburegistry.backend.dto.ClinicRequestTO;
import org.spburegistry.backend.dto.ClinicTO;

public interface ClinicService {
    public ClinicTO findByParam(Optional<Long> id, Optional<String> name);
    public Iterable<ClinicTO> findAll();
    public ClinicTO addClinic(ClinicRequestTO clinicRequestTO);
}