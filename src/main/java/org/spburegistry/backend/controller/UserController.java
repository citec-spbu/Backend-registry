package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/user")
    public UserTO getUser(@RequestParam() Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users")
    public Iterable<UserTO> getAllUsers() {
        return userService.findAll();
    }
}
