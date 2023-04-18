package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.entity.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM public.Project as p, Tag as pt WHERE pt.tag_id = :tagID AND p.id = pt.project_id AND p.start > :startDate AND (p.end IS NULL OR p.end <= :endDate)")
    List<Project> findProjectsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("tagID") Integer tagID);

}
