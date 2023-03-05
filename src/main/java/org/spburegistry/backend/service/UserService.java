package org.spburegistry.backend.service;

import org.spburegistry.backend.entity.User;

public interface UserService {
    public Iterable<User> findAll();
    public User findById(long id);
    public User findByName(String name);
}
