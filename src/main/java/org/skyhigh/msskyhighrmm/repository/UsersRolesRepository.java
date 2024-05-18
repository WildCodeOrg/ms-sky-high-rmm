package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.UsersRoles.UserRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.UsersRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRolesRepository extends JpaRepository<UsersRolesEntity, UUID> {


}
