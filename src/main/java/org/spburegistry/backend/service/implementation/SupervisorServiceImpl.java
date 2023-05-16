package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.SupervisorTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Supervisor;
import org.spburegistry.backend.repository.SupervisorRepo;
import org.spburegistry.backend.service.SupervisorService;
import org.spburegistry.backend.service.UserService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupervisorServiceImpl implements SupervisorService{
    private final SupervisorRepo supervisorRepo;
    private final UserService userService;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepo supervisorRepo, UserService userService) {
        this.supervisorRepo = supervisorRepo;
        this.userService = userService;
    }

    @Override
    public Iterable<UserTO> findAll() {
        return supervisorRepo.findAll().stream()
                .map(Supervisor::getUser)
                .map(ConvertToTO::userToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserTO findById(Long id) {
        return supervisorRepo.findById(id)
                .map(Supervisor::getUser)
                .map(ConvertToTO::userToTO)
                .orElseThrow(() -> new EntityNotFoundException("Supervisor with id " + id + " not found"));
    }

    @Override
    public SupervisorTO addSupervisor(SupervisorTO userTO) {
        return ConvertToTO.supervisorToTO(
            supervisorRepo.save(
                Supervisor.builder()
                    .link(userTO.getLink())
                    .phone(userTO.getPhone())
                    .user(userService.createUser(
                        userTO.getName(),
                        userTO.getEmail()
                    ))
                    .build()
                )
            );
    }
}
