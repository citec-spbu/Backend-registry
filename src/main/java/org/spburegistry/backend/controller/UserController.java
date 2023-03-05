package org.spburegistry.backend.controller;

import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
