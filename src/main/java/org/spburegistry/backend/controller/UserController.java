package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Get user by ID")
    public User getUser(@RequestParam() Long id) {
        return userService.findById(id);
    }

    @GetMapping("/users")
    @Operation(description = "Get all users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }
}
