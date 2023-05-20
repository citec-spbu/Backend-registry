package org.spburegistry.backend.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.spburegistry.backend.ExceptionHandler.exception.EntityAlreadyExistsException;
import org.spburegistry.backend.dto.TagTO;
import org.spburegistry.backend.entity.Tag;
import org.spburegistry.backend.repository.TagRepo;
import org.spburegistry.backend.service.TagService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.spburegistry.backend.utils.Utils.checkLimit;

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
    public Iterable<TagTO> findTagsByParameters(Optional<Boolean> sortedByWeight,
                                                Optional<String> substring,
                                                Optional<Integer> limit) {
        List<Tag> tags;
        if (substring.isPresent() && limit.isPresent()) {
            checkLimit(limit.get());
            tags = tagRepo.findByNameContainsIgnoreCase(substring.get(), PageRequest.of(0, limit.get()));
        } else if (substring.isPresent()) {
            tags = tagRepo.findByNameContainsIgnoreCase(substring.get());
        } else
            tags = limit
                    .map(lim -> {
                        checkLimit(lim);
                        return tagRepo.findAll(PageRequest.of(0, lim)).stream().toList();
                    })
                    .orElseGet(tagRepo::findAll);

        if (sortedByWeight.orElse(false)) {
            tags.sort((tag1, tag2) -> tag2.getProjects().size() - tag1.getProjects().size());
        }
        return tags.stream()
                .map(ConvertToTO::tagToTO)
                .collect(Collectors.toList());
    }
}
