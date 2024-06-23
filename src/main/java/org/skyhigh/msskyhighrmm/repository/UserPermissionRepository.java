package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.UserPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UUID> {

}
