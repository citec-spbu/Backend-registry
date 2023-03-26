package org.spburegistry.backend.controller;

import org.spburegistry.backend.entity.Project;
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
    public Iterable<Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping("/projects/project")
    public Project getProjectById(@RequestParam() Long id) {
        return projectService.findById(id);
    }
}
