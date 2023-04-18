package org.spburegistry.backend.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.spburegistry.backend.ExceptionHandler.exception.EntityAlreadyExistsException;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.entity.Tag;
import org.spburegistry.backend.repository.TagRepo;
import org.spburegistry.backend.service.TagService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    @Autowired
    public TagServiceImpl(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    @Override
    public TagTO findTagById(long id) {
        return ConvertToTO.tagToTO(tagRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id " + id + " not found")));
    }

    @Override
    public TagTO addTag(TagTO tagTO) {
        Tag newTag = tagRepo.findByNameIgnoreCase(tagTO.getName());
        if (newTag == null) {
            newTag = Tag.builder()
                    .name(tagTO.getName())
                    .build();
            tagRepo.save(newTag);
        } else {
            throw new EntityAlreadyExistsException("Tag with name " + tagTO.getName() + " already exists");
        }
        return ConvertToTO.tagToTO(newTag);

    }

    @Override
    public Iterable<TagTO> findTagsBySubstringSortedByWeight(Optional<Boolean> sortedByWeight,
            Optional<String> substring) {
        List<Tag> tags;
        if (substring.isPresent()) {
            tags = tagRepo.findByNameContainsIgnoreCase(substring.get());
        } else {
            tags = tagRepo.findAll();
        }
        if (sortedByWeight.isPresent() && sortedByWeight.get()) {
            return tags.stream()
                    .sorted((tag1, tag2) -> tag2.getProjects().size() - tag1.getProjects().size())
                    .map(ConvertToTO::tagToTO)
                    .collect(Collectors.toList());
        } else {
            return tags.stream()
                    .map(ConvertToTO::tagToTO)
                    .collect(Collectors.toList());
        }
    }
}
