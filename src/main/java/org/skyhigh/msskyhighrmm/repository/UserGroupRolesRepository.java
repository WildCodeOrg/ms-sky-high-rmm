package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserGroupRolesRepository extends JpaRepository<UserGroupRolesEntity, UUID> {
}
