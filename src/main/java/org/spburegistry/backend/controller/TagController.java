package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/data/tags")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;
    
    @GetMapping
    public Iterable<TagTO> getAllTags() {
        return tagService.findAll();
    }

    @GetMapping("/tag/id/{id}")
    public TagTO getTagById(@PathVariable Long id) {
        return tagService.findTagById(id);
    }

    @GetMapping("/tag/substring/{substring}")
    public Iterable<TagTO> getTagsBySubstring(@PathVariable String substring) {
        return tagService.findTagBySubstring(substring);
    }

    @PostMapping("/tag")
    public TagTO addNewTag(@RequestBody TagTO tagTO) {
        return tagService.addTag(tagTO);
    }
}
