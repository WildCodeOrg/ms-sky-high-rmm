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
@Table(name = "user_group_roles")
public class UserGroupRolesEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_critical", nullable = false)
    private boolean isCritical;
}
