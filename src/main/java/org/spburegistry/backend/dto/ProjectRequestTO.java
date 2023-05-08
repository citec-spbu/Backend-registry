package org.spburegistry.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.spburegistry.backend.enums.WorkFormat;

import java.util.Date;
import java.util.Set;

// you need to delete this file and all references for this file change to ProjectTO
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestTO {
    private String name;

    private String description;

    private String requirements;

    private Long clientId;

    private Set<Long> clinicsIds;

    private Set<TagTO> tags;

    private String scientificSupervisor;

    private Set<String> requirementsForPerformers;

    private WorkFormat workFormat;

    private Date start;

    private Date end;

    private int maxStudents;
}
