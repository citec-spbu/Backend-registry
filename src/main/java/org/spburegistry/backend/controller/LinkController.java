package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.LinkTO;
import org.spburegistry.backend.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/data/links")
public class LinkController {
    
    @Autowired
    private LinkService linkService;

    @GetMapping
    @Operation(description = "Get all links")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = LinkTO.class)))
    })
    public Iterable<LinkTO> getAllLinks() {
        return linkService.findAll();
    }

    @GetMapping("/link")
    @Operation(description = "Get link by id")
    public LinkTO getLinkById(@RequestParam Long id) {
        return linkService.findById(id);
    }

}
