package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ClinicRequestTO;
import org.spburegistry.backend.dto.ClinicTO;

public interface ClinicService {
    public ClinicTO findById(long id);
    public ClinicTO findByName(String name);
    public Iterable<ClinicTO> findAll();
    public ClinicTO addClinic(ClinicRequestTO clinicRequestTO);
}