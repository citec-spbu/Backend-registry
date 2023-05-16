package org.spburegistry.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuratorTO {
    private Long curatorId;
    private String name;
    private String email;
    private String link;
    private String phone;
}
