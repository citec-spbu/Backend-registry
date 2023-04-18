package org.spburegistry.backend.controller;

import java.util.Date;
import java.util.List;

import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data/projects")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectTO> getProjectsBetweenDates(
    @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
    return projectService.findProjectsBetweenDates(startDate, endDate);
    }


    @GetMapping("/project")
    public ProjectTO getProjectById(@RequestParam() Long id) {
        return projectService.findById(id);
    }

    @PostMapping("/project")
    public ProjectTO addNewProject(@RequestBody ProjectRequestTO projectRequest) {
        return projectService.addProject(projectRequest);
    }
}
