package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/data/tags")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    @Operation(description = "Get all tags with applied filters and sorting")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = TagTO.class)))
    })
    public Iterable<TagTO> getTagsBySubstringSortedByWeight(
            @RequestParam("sortedByWeight") Optional<Boolean> sortedByWeight,
            @RequestParam("substring") Optional<String> substring) {
        return tagService.findTagsBySubstringSortedByWeight(sortedByWeight, substring);
    }

    @GetMapping("/tag/id/{id}")
    @Operation(description = "Get tag by id")
    public TagTO getTagById(@PathVariable Long id) {
        return tagService.findTagById(id);
    }

    @PostMapping("/tag")
    @Operation(description = "Add new tag")
    public TagTO addNewTag(@RequestBody TagTO tagTO) {
        return tagService.addTag(tagTO);
    }
}
