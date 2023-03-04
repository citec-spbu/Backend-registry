package org.spburegistry.backend.controller;

import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/")
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping("/users/{username}")
    // public User getUserByName(@PathVariable String name) {
    //     return userService.findByName(name);
    // }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findById(id);
    }
}
