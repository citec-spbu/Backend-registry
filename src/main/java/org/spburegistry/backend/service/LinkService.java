package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.LinkTO;

public interface LinkService {
    Iterable<LinkTO> findAll();
    LinkTO findById(Long id);
}
