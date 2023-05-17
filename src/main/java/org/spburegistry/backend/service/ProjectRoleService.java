package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.RoleTO;

public interface ProjectRoleService {
    Iterable<RoleTO> findAll();
    RoleTO findById(Long id);
}
