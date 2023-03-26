package org.spburegistry.backend.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.NoSuchEntityException;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.UserService;
import org.spburegistry.backend.utils.ConvertToTO;
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
    public UserTO findById(long id) {
        User user = userRepo.findById(id).orElseThrow(
            () -> new NoSuchEntityException("User with id " + id + " not found"));
        return ConvertToTO.userToTO(user);
    }

    @Override
    public Set<UserTO> findAll() {
        return userRepo.findAll().stream()
            .map(user -> ConvertToTO.userToTO(user))
            .collect(Collectors.toSet());
    }
    
}
