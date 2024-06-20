package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RolesOperationsRepository extends JpaRepository<RolesOperationsEntity, UUID> {
    @Query(value = "SELECT * FROM public.roles_operations ro WHERE ro.role_id = ?1", nativeQuery = true)
    List<RolesOperationsEntity> findByRoleId(UUID roleId);

    @Query(value = "SELECT * FROM public.roles_operations ro WHERE ro.permission_id = ?1", nativeQuery = true)
    List<RolesOperationsEntity> findByPermissionId(UUID permissionId);

    @Query(value = "SELECT * FROM public.roles_operations ro WHERE ro.role_id = ?1 AND ro.permission_id = ?2", nativeQuery = true)
    List<RolesOperationsEntity> findByRoleIdAndPermissionId(UUID roleId, UUID permissionId);
}
