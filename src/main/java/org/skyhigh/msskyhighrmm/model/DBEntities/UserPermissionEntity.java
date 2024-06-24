package org.skyhigh.msskyhighrmm.model.DBEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_permission")
public class UserPermissionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UniversalUserEntity userId;

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    OperationPermissionsEntity permissionId;

    @Column(name = "create_date")
    @Length(min = 24, max = 24)
    String createDate;
}
