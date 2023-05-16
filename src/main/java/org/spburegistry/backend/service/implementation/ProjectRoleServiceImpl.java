package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.RoleTO;
import org.spburegistry.backend.repository.RoleInProjectRepo;
import org.spburegistry.backend.service.ProjectRoleService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService{
    private final RoleInProjectRepo projectRoleRepo;

    @Autowired
    public ProjectRoleServiceImpl(RoleInProjectRepo projectRoleRepo) {
        this.projectRoleRepo = projectRoleRepo;
    }

    @Override
    public Iterable<RoleTO> findAll() {
        return projectRoleRepo.findAll().stream()
                .map(ConvertToTO::roleToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public RoleTO findById(Long id) {
        return ConvertToTO.roleToTO(projectRoleRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project Role with id " + id + " not found")));
    }
}
