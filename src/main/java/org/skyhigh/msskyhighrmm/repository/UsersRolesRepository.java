package org.skyhigh.msskyhighrmm.repository;

import jakarta.transaction.Transactional;
import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UsersRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UsersRolesRepository extends JpaRepository<UsersRolesEntity, UUID> {

    @Query(value = "SELECT * FROM users_roles WHERE user_id = ?1", nativeQuery = true)
    List<UsersRolesEntity> findByUserId(UUID userId);

    @Query(value = "SELECT * FROM users_roles WHERE role_id = ?1", nativeQuery = true)
    List<UsersRolesEntity> findByRoleId(UUID roleId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM users_roles WHERE role_id = ?1", nativeQuery = true)
    void deleteByRoleId(UUID roleId);
}
