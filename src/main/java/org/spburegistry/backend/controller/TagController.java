package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/data/tags")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;
    
    @GetMapping
    public Iterable<TagTO> getAllTags() {
        return tagService.findAll();
    }

    @GetMapping("/tag/id")
    public TagTO getTagById(@RequestParam Long id) {
        return tagService.findTagById(id);
    }

    @GetMapping("/tag/regex")
    public Iterable<TagTO> getTagsByRegex(@RequestParam String regex) {
        return tagService.findTagByRegex(regex);
    }

    @PostMapping("/tag")
    public TagTO addNewTag(@RequestBody TagTO tagTO) {
        return tagService.addTag(tagTO);
    }
}
