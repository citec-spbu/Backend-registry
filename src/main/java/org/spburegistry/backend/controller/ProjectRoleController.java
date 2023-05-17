package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.RoleTO;
import org.spburegistry.backend.service.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/data/projectRoles")
public class ProjectRoleController {
    
    @Autowired
    private ProjectRoleService projectRoleService;

    @GetMapping
    @Operation(description = "Get all project Roles")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = RoleTO.class)))
    })
    public Iterable<RoleTO> getAllProjectRoles() {
        return projectRoleService.findAll();
    }

    @GetMapping("/projectRole")
    @Operation(description = "Get project Role by id")
    public RoleTO getProjectRoleById(@RequestParam Long id) {
        return projectRoleService.findById(id);
    }
}
