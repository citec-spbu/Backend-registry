package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.repository.ClientRepo;
import org.spburegistry.backend.service.ClientService;
import org.spburegistry.backend.service.UserService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final UserService userService;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo, UserService userService) {
        this.clientRepo = clientRepo;
        this.userService = userService;
    }

    @Override
    public Iterable<UserTO> findAll() {
        return clientRepo.findAll().stream()
                .map(Client::getUser)
                .map(ConvertToTO::userToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserTO findById(long id) {
        return clientRepo.findById(id)
                .map(Client::getUser)
                .map(ConvertToTO::userToTO)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));
    }

    @Override
    public ClientTO addClient(ClientTO clientTO) {
        return ConvertToTO.clientToTO(
            clientRepo.save(
                Client.builder()
                    .link(clientTO.getLink())
                    .organizationName(clientTO.getOrgName())
                    .phone(clientTO.getPhone())
                    .user(userService.createUser(
                        clientTO.getName(),
                        clientTO.getEmail()
                    ))
                    .build()
                )
            );
    }

}
