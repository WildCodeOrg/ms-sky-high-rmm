package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.AdministratorKeyCodeEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OperationPermissionsRepository extends JpaRepository<OperationPermissionsEntity, UUID> {
    @Query(value = "SELECT * FROM operation_permissions WHERE permission_name = ?1", nativeQuery = true)
    List<OperationPermissionsEntity> findByPermissionName(String name);

    @Query(value = "SELECT * FROM operation_permissions WHERE operation_endpoint = ?1", nativeQuery = true)
    List<OperationPermissionsEntity> findByEndpoint(String endpoint);
}
