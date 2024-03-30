package org.skyhigh.msskyhighrmm.model.DBEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operation_permissions")
public class OperationPermissionsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "permission_name", nullable = false)
    private String permission_name;

    @Column(name = "operation_endpoint", nullable = false)
    private String operation_endpoint;

    @Column(name = "is_critical", nullable = false)
    private boolean is_critical;
}
