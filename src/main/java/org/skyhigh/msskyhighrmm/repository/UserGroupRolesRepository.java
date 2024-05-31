package org.skyhigh.msskyhighrmm.repository;

import jakarta.transaction.Transactional;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserGroupRolesRepository extends JpaRepository<UserGroupRolesEntity, UUID> {
    /**
     * Метод для поиска ролей по имени
     * @param roleName - имя роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE role_name = ?1", nativeQuery = true)
    List<UserGroupRolesEntity> findByRoleName(String roleName);

    /**
     * Метод поиска ролей по совпадению имени, описания и критичности
     * @param roleName - имя роли
     * @param description - описание роли
     * @param isCritical - критичность роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE role_name = ?1 " +
            "AND description = ?2 AND is_critical = ?3", nativeQuery = true)
    List<UserGroupRolesEntity> findByRoleNameANDDescriptionANDCriticality(
        String roleName,
        String description,
        boolean isCritical
    );

    /**
     * Метод поиска по ролей совпадению наименования и критичности
     * @param roleName - имя роли
     * @param isCritical - критичность роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE role_name = ?1 " +
            " AND is_critical = ?2", nativeQuery = true)
    List<UserGroupRolesEntity> findByRoleNameANDCriticality(
            String roleName,
            boolean isCritical
    );

    /**
     * Метод поиска ролей по описанию и критичности
     * @param description - описание роли
     * @param isCritical - критичность роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE " +
            "description = ?1 AND is_critical = ?2", nativeQuery = true)
    List<UserGroupRolesEntity> findByDescriptionANDCriticality(
            String description,
            boolean isCritical
    );

    /**
     * Метод поиска ролей по имени и описанию
     * @param roleName - имя роли
     * @param description - описание роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE role_name = ?1 " +
            "AND description = ?2", nativeQuery = true)
    List<UserGroupRolesEntity> findByRoleNameANDDescription(
            String roleName,
            String description
    );

    /**
     * Метод поиска ролей по описанию
     * @param description - описание роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE description = ?1", nativeQuery = true)
    List<UserGroupRolesEntity> findByDescription(
            String description
    );

    /**
     * Метод поиска ролей по критичности
     * @param isCritical - критичность роли
     * @return Список найденных ролей
     */
    @Query(value = "SELECT * FROM user_group_roles WHERE is_critical = ?1", nativeQuery = true)
    List<UserGroupRolesEntity> findByCriticality(
            boolean isCritical
    );

    @Query(value = "SELECT ugr.id, ugr.role_name, ugr.description, ugr.is_critical " +
            "FROM user_group_roles ugr " +
            "JOIN users_roles ur on ugr.id = ur.role_id " +
            "WHERE ur.user_id = ?1", nativeQuery = true)
    List<UserGroupRolesEntity> getRolesOfUser(UUID userId);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE user_group_roles SET role_name = ?2, description = ?3 WHERE id = ?1", nativeQuery = true)
    void updateRoleAllArgsById(UUID roleId, String roleName, String description);
}
