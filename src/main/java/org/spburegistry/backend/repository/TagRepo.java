package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findByNameIgnoreCase(String name);

    List<Tag> findByNameContainsIgnoreCase(String substring);

    List<Tag> findByNameContainsIgnoreCase(String substring, Pageable pageable);
}
