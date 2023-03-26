package org.spburegistry.backend.service.implementation;

import org.spburegistry.backend.ExceptionHandler.exception.NoSuchEntityException;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(long id) {
        return userRepo.findById(id).orElseThrow(
            () -> new NoSuchEntityException("User with id " + id + " not found"));
    }

    @Override
    public User findByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
    
}
