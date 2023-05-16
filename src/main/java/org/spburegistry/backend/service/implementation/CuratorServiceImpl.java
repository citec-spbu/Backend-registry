package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.CuratorTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Curator;
import org.spburegistry.backend.repository.CuratorRepo;
import org.spburegistry.backend.service.CuratorService;
import org.spburegistry.backend.service.UserService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CuratorServiceImpl implements CuratorService {
    private final CuratorRepo curatorRepo;
    private final UserService userService;

    @Autowired
    public CuratorServiceImpl(CuratorRepo curatorRepo, UserService userService) {
        this.curatorRepo = curatorRepo;
        this.userService = userService;
    }

    @Override
    public Iterable<UserTO> findAll() {
        return curatorRepo.findAll().stream()
                .map(Curator::getUser)
                .map(ConvertToTO::userToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserTO findById(Long id) {
        return curatorRepo.findById(id)
                .map(Curator::getUser)
                .map(ConvertToTO::userToTO)
                .orElseThrow(() -> new EntityNotFoundException("Curator with id " + id + " not found"));
    }

    @Override
    public CuratorTO addCurator(CuratorTO userTO) {
        return ConvertToTO.curatorToTO(
            curatorRepo.save(
                Curator.builder()
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
