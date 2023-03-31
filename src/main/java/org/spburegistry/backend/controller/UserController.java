package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public UserTO getUserById(@RequestParam() Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public Iterable<UserTO> getAllUsers() {
        return userService.findAll();
    }
}
