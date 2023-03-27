package org.spburegistry.backend.service.implementation;

import java.util.Date;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.repository.ClientRepo;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.ClientService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final UserRepo userRepo;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo, UserRepo userRepo) {
        this.clientRepo = clientRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ClientTO addNewClient(ClientTO clientTO) {
        Client client = clientRepo.getReferenceById(clientTO.getClientId());
        if (client == null) {
            client = Client.builder()
                    .link(clientTO.getLink())
                    .organizationName(clientTO.getOrgName())
                    .phone(clientTO.getPhone())
                    .build();
            client.setCreationTime(new Date());
            client.setLastUpdateTime(new Date());
            User user = User.builder()
                    .name(clientTO.getName())
                    .email(clientTO.getEmail())
                    .build();
            user.setCreationTime(new Date());
            user.setLastUpdateTime(new Date());
            user.setClient(client);
            client.setUser(user);
            clientRepo.save(client);
            userRepo.save(user);
        }
        return ConvertToTO.clientToTO(client);
    }

}
