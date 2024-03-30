package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Permissions.OperationPermission;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationPermissionsRepository extends JpaRepository<OperationPermissionsEntity, UUID> {
}
