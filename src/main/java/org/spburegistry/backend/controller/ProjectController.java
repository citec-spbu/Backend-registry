package org.spburegistry.backend.controller;

import java.util.Date;
import java.util.List;

import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.enums.Sort;
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

    @GetMapping("")
    public Iterable<ProjectTO> getProjects(
            @RequestParam(required = false, defaultValue = "") String string_to_search,
            @RequestParam(required = false, defaultValue = "1990") @DateTimeFormat(pattern = "yyyy") Date startDate,
            @RequestParam(required = false, defaultValue = "3000") @DateTimeFormat(pattern = "yyyy") Date endDate,
            @RequestParam(required = false, defaultValue = "NULL") Sort sorting_by_Date,
            @RequestParam(required = false, defaultValue = "[]") List<String> tags_from_request,
            @RequestParam(required = false, defaultValue = "[]") List<String> clinics_from_request) {
        return projectService.getProjects(string_to_search, startDate, endDate, sorting_by_Date, tags_from_request,
                clinics_from_request);
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
