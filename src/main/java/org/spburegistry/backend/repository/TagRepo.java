package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
    public Tag findByName(String name);
}
