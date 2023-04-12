package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculty")
    @Operation(description = "Get faculty by ID")
    public FacultyTO getFacultyById(@RequestParam() Long id) {
        return facultyService.findById(id);
    }

    @GetMapping
    @Operation(description = "Get all faculties")
    public Iterable<FacultyTO> getAllFaculties() {
        return facultyService.findAll();
    }
}

