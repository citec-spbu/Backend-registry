package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public Iterable<ProjectTO> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/projects/project")
    public ProjectTO getProjectById(@RequestParam() Long id) {
        return projectService.findById(id);
    }
}
