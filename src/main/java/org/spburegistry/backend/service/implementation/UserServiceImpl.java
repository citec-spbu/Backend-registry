package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.enums.Role;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.UserService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

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
            () -> new EntityNotFoundException("User with id " + id + " not found"));
        return ConvertToTO.userToTO(user);
    }

    @Override
    public Iterable<UserTO> findAll() {
        return userRepo.findAll().stream()
            .map(ConvertToTO::userToTO)
            .collect(Collectors.toSet());
    }

    @Override
    public User createUser(String name, String email) {
        User newUser = User.builder()
                .role(Role.USER)
                .name(name)
                .email(email)
                .build();
        return userRepo.save(newUser);
    }
    
}
