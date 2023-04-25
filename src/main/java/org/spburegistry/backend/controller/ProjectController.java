package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/data/projects")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(description = "Get all projects")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProjectTO.class)))
    })
    public Iterable<ProjectTO> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project")
    @Operation(description = "Get project by id")
    public ProjectTO getProjectById(@RequestParam() Long id) {
        return projectService.findById(id);
    }

    @PostMapping("/project")
    @Operation(description = "Add new project")
    public ProjectTO addNewProject(@RequestBody ProjectRequestTO projectRequest) {
        return projectService.addProject(projectRequest);
    }
}
