package org.spburegistry.backend.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitTO {
    private Long commitId;
    private Date commitDate;
    private int numDifferences;
}
