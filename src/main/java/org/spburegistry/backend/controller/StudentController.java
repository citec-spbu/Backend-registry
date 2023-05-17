package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.StudentTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.StudentService;
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
@RequestMapping("/data/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping
    @Operation(description = "Get all students")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UserTO.class)))
    })
    public Iterable<UserTO> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/student")
    @Operation(description = "Get student by id")
    public UserTO getStudentById(@RequestParam Long id) {
        return studentService.findById(id);
    }

    @PostMapping("/student")
    @Operation(description = "Add new student")
    public StudentTO addNewStudent(@RequestBody StudentTO studentTO) {
        return studentService.addStudent(studentTO);
    }
}
