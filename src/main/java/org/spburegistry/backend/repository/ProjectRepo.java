package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.enums.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p JOIN p.tags t JOIN p.clinics c WHERE " +
            "(" +
            "LOWER(CAST(p.id AS text)) LIKE %:stringToSearch% " +
            "OR LOWER(CAST(p.description AS text)) LIKE %:stringToSearch% " +
            "OR LOWER(CAST(p.name AS text)) LIKE %:stringToSearch% " +
            "OR LOWER(CAST(p.requirements AS text)) LIKE %:stringToSearch% " +
            "OR LOWER(CAST(p.resultLink AS text)) LIKE %:stringToSearch% " +
            "OR LOWER(CAST(p.scientificSupervisor AS text)) LIKE %:stringToSearch% " +
            "OR LOWER(CAST(p.workFormat AS text)) LIKE %:stringToSearch%) " +
            "AND (p.start >= :startDate OR p.start IS NULL) " +
            "AND (p.end <= :endDate OR p.end IS NULL) " +
            "AND (COALESCE(:tagsFromRequest, '') = '' OR EXISTS (SELECT 1 FROM p.tags t WHERE t.name IN :tagsFromRequest)) " +
            "AND (COALESCE(:clinicsFromRequest, '') = '' OR c.name IN :clinicsFromRequest) " +
            "ORDER BY " +
            "CASE WHEN :sortingByDate = 'ASC' THEN p.start END ASC, " +
            "CASE WHEN :sortingByDate = 'DESC' THEN p.start END DESC, " +
            "CASE WHEN :sortingByDate = 'NULL' THEN NULL END")

    List<Project> getProjects(@Param("stringToSearch") String stringToSearch, @Param("startDate") Date startDate,
            @Param("endDate") Date endDate, @Param("tagsFromRequest") List<String> tagsFromRequest,
            @Param("clinicsFromRequest") List<String> clinicsFromRequest,
            @Param("sortingByDate") String sortingByDate);

}
