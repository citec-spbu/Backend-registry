package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.spburegistry.backend.dto.FacultyRequestTO;
import org.spburegistry.backend.dto.FacultyTO;
import org.spburegistry.backend.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = FacultyTO.class)))
    })
    public Iterable<FacultyTO> getAllFaculties() {
        return facultyService.findAll();
    }

    @PostMapping("/faculty")
    @Operation(description = "Add new faculty")
    public FacultyTO addNewClinic(@RequestBody FacultyRequestTO facultyRequestTO) {
        return facultyService.addFaculty(facultyRequestTO);
    }
}

