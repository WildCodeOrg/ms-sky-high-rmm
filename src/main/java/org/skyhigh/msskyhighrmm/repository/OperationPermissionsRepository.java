package org.skyhigh.msskyhighrmm.repository;

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

    @Query(value = "SELECT * FROM operation_permissions WHERE is_critical = ?1", nativeQuery = true)
    List<OperationPermissionsEntity> findByCriticality(boolean isCritical);

    @Query(value = "SELECT * FROM operation_permissions WHERE permission_name = ?1" +
            " AND operation_endpoint = ?2 AND is_critical = ?3", nativeQuery = true)
    List<OperationPermissionsEntity> findByAllArgs(String permissionName, String permissionEndpoint, boolean isCritical);

    @Query(value = "SELECT * FROM operation_permissions WHERE permission_name = ?1" +
            " AND operation_endpoint = ?2", nativeQuery = true)
    List<OperationPermissionsEntity> findByNameAndEndpoint(String permissionName, String permissionEndpoint);

    @Query(value = "SELECT * FROM operation_permissions WHERE permission_name = ?1" +
            " AND is_critical = ?2", nativeQuery = true)
    List<OperationPermissionsEntity> findByNameAndCriticality(String permissionName, boolean isCritical);

    @Query(value = "SELECT * FROM operation_permissions WHERE operation_endpoint = ?1" +
            " AND is_critical = ?2", nativeQuery = true)
    List<OperationPermissionsEntity> findByEndpointAndCriticality(String permissionEndpoint, boolean isCritical);

    @Query(value = "SELECT op.id, op.permission_name, op.operation_endpoint, op.is_critical " +
            "FROM public.operation_permissions op JOIN public.roles_operations ro " +
            "ON ro.permission_id = op.id WHERE ro.role_id = ?1", nativeQuery = true)
    List<OperationPermissionsEntity> findOperationPermissionsByRoleId(UUID roleId);
}
