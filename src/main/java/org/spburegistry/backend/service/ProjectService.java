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

    Iterable<ProjectTO> getProjects(String stringToSearch, Date startDate, Date endDate,
            Sort sortingByDate, List<String> tagsFromRequest, List<String> clinicsFromRequest);
}
