package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.UserPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UUID> {
    @Query(value = "SELECT * FROM public.user_permission up WHERE up.user_id = ?1", nativeQuery = true)
    List<UserPermissionEntity> findByUserId(UUID userId);

    @Query(value = "SELECT * FROM public.user_permission up WHERE up.user_id = ?1 AND up.permission_id = ?2", nativeQuery = true)
    List<UserPermissionEntity> findByUserIdAndPermissionId(UUID userId, UUID permissionId);

    @Query(value = "SELECT * FROM public.user_permission up WHERE up.permission_id = ?1", nativeQuery = true)
    List<UserPermissionEntity> findByPermissionId(UUID permissionId);
}
