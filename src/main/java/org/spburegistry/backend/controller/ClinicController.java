package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.spburegistry.backend.dto.ClinicRequestTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data/clinics")
@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping
    @Operation(description = "Get all clinics")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ClinicTO.class)))
    })
    public Iterable<ClinicTO> getAllClinics() {
        return clinicService.findAll();
    }

    @GetMapping("/clinic/id/{id}")
    @Operation(description = "Get clinic by id")
    public ClinicTO getClinicById(@PathVariable Long id) {
        return clinicService.findById(id);
    }

    @GetMapping("/clinic/name/{name}")
    @Operation(description = "Get clinic by name")
    public ClinicTO getClinicByName(@PathVariable String name) {
        return clinicService.findByName(name);
    }

    @PostMapping("/clinic")
    @Operation(description = "Add new clinic")
    public ClinicTO addNewClinic(@RequestBody ClinicRequestTO clinicRequestTO) {
        return clinicService.addClinic(clinicRequestTO);
    }
}
