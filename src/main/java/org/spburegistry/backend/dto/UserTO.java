package org.spburegistry.backend.dto;

import org.spburegistry.backend.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTO {
    private Long userId;
    private Role role;
    private StudentTO student;
    private ClientTO client;
    private CuratorTO curator;
    private SupervisorTO supervisor;
}
