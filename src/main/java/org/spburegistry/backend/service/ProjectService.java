package org.spburegistry.backend.service;

import java.util.Date;
import java.util.List;

import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.enums.Sort;

public interface ProjectService {
    Iterable<ProjectTO> findAll();

    ProjectTO findById(long id);

    ProjectTO addProject(ProjectRequestTO project);

    Iterable<ProjectTO> getProjects(String string_to_search, Date startDate, Date endDate,
            Sort sorting_by_Date, List<String> tags_from_request, List<String> clinics_from_request);
}
