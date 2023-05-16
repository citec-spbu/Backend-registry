package org.spburegistry.backend.dto;

import java.util.Date;
import java.util.Set;

import org.spburegistry.backend.enums.Status;
import org.spburegistry.backend.enums.WorkFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTO {
    private Long projectId;
    private String name;
    private Set<TagTO> tags;
    private Set<Long> clinicsIds;
    private Set<Long> clientsIds;
    private Set<Long> curatorsIds;
    private Set<Long> supervisorIds;
    private String description;
    private Set<LinkTO> links;
    private Set<RoleTO> projectRoles;
    private String requirements;
    private String requirementsForPerformers;
    private Date startTime;
    private Date startFiling;
    private Date endFiling;
    private Date startImplementation;
    private Date endImplementation;
    private Date startDefense;
    private Date endDefense;
    private Status status;
    private Set<Long> linkedProjectsIds;
    private WorkFormat workFormat;
    private int maxStudents;
}
