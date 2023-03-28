package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.ProjectRequest;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public Iterable<ProjectTO> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/project")
    public ProjectTO getProjectById(@RequestParam() Long id) {
        return projectService.findById(id);
    }

    @PostMapping("/project")
    public ProjectTO addNewProject(@RequestBody ProjectRequest projectRequest) {
        return projectService.addProject(projectRequest);
    }
}
