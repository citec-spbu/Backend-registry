package org.spburegistry.backend.utils;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.ProjectTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.Project;

public class ConvertFromTO {
    public static Client clientFromTO(ClientTO clientTO) {
        return Client.builder().link(null).build();
            
    }

    public static Project projectFromTO(ProjectTO projectTO) {
        return Project.builder()
            .client(null).build();
    }
}
