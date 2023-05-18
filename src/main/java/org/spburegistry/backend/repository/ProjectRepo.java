package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p ORDER BY RANDOM() LIMIT :lim")
    List<Project> getRandomProjects(@Param("lim") long limit);
}
