package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepo extends JpaRepository<Commit, Long> {
    
}
