package org.spburegistry.backend.service.implementation;

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
    public Iterable<TagTO> findAll() {
        return tagRepo.findAll().stream().map(ConvertToTO::tagToTO)
                    .collect(Collectors.toSet());
    }

    @Override
    public TagTO findTagById(long id) {
        return ConvertToTO.tagToTO(tagRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id " + id + " not found")));
    }

    
    @Override
    public Iterable<TagTO> findTagByRegex(String regex) {
        return tagRepo.findByNameContainsIgnoreCase(regex).stream().map(ConvertToTO::tagToTO)
                    .collect(Collectors.toSet());
    }
    
    @Override
    public TagTO addTag(TagTO tagTO) {
        Optional.ofNullable(tagRepo.findByNameIgnoreCase(tagTO.getName()))
                .ifPresent(tag -> new EntityAlreadyExistsException("Tag with name " + tag.getName() + " already exists"));
        Tag newTag = Tag.builder()
                        .name(tagTO.getName())
                        .build();
        Tag tag = tagRepo.save(newTag);
        return ConvertToTO.tagToTO(tag);          
        
    }
}
