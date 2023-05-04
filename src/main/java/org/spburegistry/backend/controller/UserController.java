package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UserTO.class)))
    })
    public Iterable<UserTO> getAllUsers() {
        return userService.findAll();
    }
}
