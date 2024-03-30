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
@Table(name = "roles_operations")
public class RolesOperationsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    UserGroupRolesEntity role_id;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    OperationPermissionsEntity permission_id;
}
