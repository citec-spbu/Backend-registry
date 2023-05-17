package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.SupervisorTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/data/supervisors")
public class SupervisorController {
    
    @Autowired
    private SupervisorService supervisorService;

    @GetMapping
    @Operation(description = "Get all supervisors")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UserTO.class)))
    })
    public Iterable<UserTO> getAllSupervisors() {
        return supervisorService.findAll();
    }

    @GetMapping("/supervisor")
    @Operation(description = "Get supervisor by id")
    public UserTO getSupervisorById(@RequestParam Long id) {
        return supervisorService.findById(id);
    }

    @PostMapping("/supervisor")
    @Operation(description = "Add new supervisor")
    public SupervisorTO addNewSupervisor(@RequestBody SupervisorTO supervisorTO) {
        return supervisorService.addSupervisor(supervisorTO);
    }
}
