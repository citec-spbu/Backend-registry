package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.UserTO;

public interface ClientService {
    public Iterable<UserTO> findAll();
    public UserTO findById(long id);
    public ClientTO addClient(ClientTO userTO); 
}
