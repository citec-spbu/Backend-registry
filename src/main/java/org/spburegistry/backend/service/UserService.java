package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.UserTO;

public interface UserService {
    public Iterable<UserTO> findAll();
    public UserTO findById(long id);
}
