package org.spburegistry.backend.service;

import java.util.Optional;

import org.spburegistry.backend.dto.TagTO;

public interface TagService {
    TagTO findTagById(long id);
    TagTO addTag(TagTO tagTO);
    Iterable<TagTO> findTagsBySubstringSortedByWeight(Optional<Boolean> sortedByWeight, Optional<String> substring);
}
