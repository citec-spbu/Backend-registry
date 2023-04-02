package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.TagTO;

public interface TagService {
    public Iterable<TagTO> findAll();
    public TagTO findTagById(long id);
    public Iterable<TagTO> findTagByRegex(String regex);
    public TagTO addTag(TagTO tagTO);
}
