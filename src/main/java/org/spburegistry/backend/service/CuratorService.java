package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.CuratorTO;
import org.spburegistry.backend.dto.UserTO;

public interface CuratorService {
    Iterable<UserTO> findAll();
    UserTO findById(Long id);
    CuratorTO addCurator(CuratorTO userTO);
}
