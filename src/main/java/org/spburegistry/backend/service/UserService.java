package org.spburegistry.backend.service;

import java.util.Set;

import org.spburegistry.backend.dto.UserTO;

public interface UserService {
    public Set<UserTO> findAll();
    public UserTO findById(long id);
}
