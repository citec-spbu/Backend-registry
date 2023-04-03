package org.spburegistry.backend.repository;

import java.util.List;

import org.spburegistry.backend.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    public Tag findByName(String name);
    public Tag findByNameIgnoreCase(String name);
    List<Tag> findByNameContainsIgnoreCase(String substring);
}
