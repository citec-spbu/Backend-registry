package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.UserTO;

public interface ClientService {
    Iterable<UserTO> findAll();
    UserTO findById(long id);
    ClientTO addClient(ClientTO userTO); 
}
