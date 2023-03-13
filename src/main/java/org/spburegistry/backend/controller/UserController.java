package org.spburegistry.backend.controller;

import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/user")
    public User getUser(@RequestParam() Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }
}
