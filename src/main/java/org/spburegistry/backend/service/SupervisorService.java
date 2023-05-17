package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.SupervisorTO;
import org.spburegistry.backend.dto.UserTO;

public interface SupervisorService {
    Iterable<UserTO> findAll();
    UserTO findById(Long id);
    SupervisorTO addSupervisor(SupervisorTO userTO);
}
