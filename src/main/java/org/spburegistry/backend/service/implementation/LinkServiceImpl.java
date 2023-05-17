package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.LinkTO;
import org.spburegistry.backend.repository.LinkRepo;
import org.spburegistry.backend.service.LinkService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepo linkRepo;

    @Autowired
    public LinkServiceImpl(LinkRepo linkRepo) {
        this.linkRepo = linkRepo;
    }

    @Override
    public Iterable<LinkTO> findAll() {
        return linkRepo.findAll().stream()
                .map(ConvertToTO::linkToTO)
                .collect(Collectors.toSet());
    }

    @Override
    public LinkTO findById(Long id) {
        return ConvertToTO.linkToTO(linkRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Link with id " + id + " not found")));
    }

}
