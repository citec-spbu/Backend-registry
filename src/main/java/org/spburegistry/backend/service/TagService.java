package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.TagTO;

import java.util.Optional;

public interface TagService {
    TagTO findTagById(long id);

    TagTO addTag(TagTO tagTO);

    Iterable<TagTO> findTagsByParameters(Optional<Boolean> sortedByWeight,
                                         Optional<String> substring,
                                         Optional<Integer> limit);
}
