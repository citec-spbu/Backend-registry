package org.spburegistry.backend.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTO {
    private String name;

    private String description;

    private String requirements;

    // private ClientTO client;

    private Set<StudentTO> students;

    private Set<ClinicTO> clinics;

    private Set<String> tags;

    private String scientificSupervisor;

    private String resultLink;

    private Set<CommitTO> commits;

    private Set<String> links;

    private Set<String> requirementsForPerformers;

    private String workFormat;

    private Date start;

    private Date end;

    private int maxStudents;
}
