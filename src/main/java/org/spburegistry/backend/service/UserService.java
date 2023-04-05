package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.User;

public interface UserService {
    Iterable<UserTO> findAll();
    UserTO findById(long id);
    User createUser(String name, String email);
}
