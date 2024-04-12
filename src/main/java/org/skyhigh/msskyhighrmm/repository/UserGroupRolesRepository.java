package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserGroupRolesRepository extends JpaRepository<UserGroupRolesEntity, UUID> {
    //поиск по наименованию роли
    @Query(value = "SELECT * FROM user_group_roles WHERE role_name = ?1", nativeQuery = true)
    List<RolesOperationsEntity> findByRoleName(String roleName);
}
