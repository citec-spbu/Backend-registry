package org.spburegistry.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UpdateTimestamp
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @CreationTimestamp
    @Column(name = "creation_time")
    private Date creationTime;

    @PrePersist
    protected void onCreate() {
        lastUpdateTime = creationTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateTime = new Date();
    }
}
