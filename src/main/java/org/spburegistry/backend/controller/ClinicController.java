package org.spburegistry.backend.controller;

import java.util.Optional;

import org.spburegistry.backend.dto.ClinicRequestTO;
import org.spburegistry.backend.dto.ClinicTO;
import org.spburegistry.backend.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data/clinics")
@RestController
public class ClinicController {
    
    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public Iterable<ClinicTO> getAllClinics() {
        return clinicService.findAll();
    }

    @GetMapping("/clinic")
    public ClinicTO getClinicById(
        @RequestParam Optional<Long> id, 
        @RequestParam Optional<String> name
    ) {
        return clinicService.findByParam(id, name);
    }

    @PostMapping("/clinic")
    public ClinicTO addNewClinic(@RequestBody ClinicRequestTO clinicRequestTO) {
        return clinicService.addClinic(clinicRequestTO);
    }
}
