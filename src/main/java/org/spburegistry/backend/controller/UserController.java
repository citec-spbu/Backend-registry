package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.UserTO;
import io.swagger.v3.oas.annotations.Operation;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @Operation(description = "Get user by ID")
    public UserTO getUserById(@RequestParam() Long id) {
        return userService.findById(id);
    }

    @GetMapping
    @Operation(description = "Get all users")
    public Iterable<UserTO> getAllUsers() {
        return userService.findAll();
    }
}
