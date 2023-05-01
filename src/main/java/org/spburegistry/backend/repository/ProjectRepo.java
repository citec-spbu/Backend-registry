package org.spburegistry.backend.repository;

import org.spburegistry.backend.entity.Project;
import org.spburegistry.backend.enums.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    // WHERE cast(p.id as text) LIKE %:string_to_search%"'

    @Query("SELECT p FROM Project p JOIN p.tags t JOIN p.clinics c WHERE " +
            "(" +
            "LOWER(CAST(p.id AS text)) LIKE %:string_to_search% " +
            "OR LOWER(CAST(p.description AS text)) LIKE %:string_to_search% " +
            "OR LOWER(CAST(p.name AS text)) LIKE %:string_to_search% " +
            "OR LOWER(CAST(p.requirements AS text)) LIKE %:string_to_search% " +
            "OR LOWER(CAST(p.resultLink AS text)) LIKE %:string_to_search% " +
            "OR LOWER(CAST(p.scientificSupervisor AS text)) LIKE %:string_to_search% " +
            "OR LOWER(CAST(p.workFormat AS text)) LIKE %:string_to_search%) " +
            "AND (p.start >= :startDate OR p.start IS NULL) " +
            "AND (p.end <= :endDate OR p.end IS NULL) " +
            "AND (COALESCE(:tags_from_request) = '[]' OR t.name IN (:tags_from_request)) " +
            "AND (COALESCE(:clinics_from_request) = '[]' OR c.name IN (:clinics_from_request)) " +
            "ORDER BY " +
            "CASE WHEN :sorting_by_Date = 'ASC' THEN p.start END ASC, " +
            "CASE WHEN :sorting_by_Date = 'DESC' THEN p.start END DESC, " +
            "CASE WHEN :sorting_by_Date = 'NULL' THEN NULL END")
    // "AND (t.name IN (:tags_from_request)) ")
    List<Project> getProjects(@Param("string_to_search") String string_to_search, @Param("startDate") Date startDate,
            @Param("endDate") Date endDate, @Param("tags_from_request") List<String> tags_from_request,
            @Param("clinics_from_request") List<String> clinics_from_request,
            @Param("sorting_by_Date") String sorting_by_Date);

}
