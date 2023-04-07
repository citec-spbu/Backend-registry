package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.TagTO;

public interface TagService {
    Iterable<TagTO> findAll();
    TagTO findTagById(long id);
    Iterable<TagTO> findTagBySubstring(String substring);
    TagTO addTag(TagTO tagTO);
}
