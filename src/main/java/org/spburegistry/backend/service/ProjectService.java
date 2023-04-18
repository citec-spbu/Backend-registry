package org.spburegistry.backend.service;

import org.spburegistry.backend.dto.ProjectRequestTO;
import org.spburegistry.backend.dto.ProjectTO;
import java.util.Date;
import java.util.List;

public interface ProjectService {
    Iterable<ProjectTO> findAll();
    ProjectTO findById(long id);
    ProjectTO addProject(ProjectRequestTO project);
    List<ProjectTO> findProjectsBetweenDates(Date startDate, Date endDate);
}
