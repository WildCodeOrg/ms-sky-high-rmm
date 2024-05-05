package org.skyhigh.msskyhighrmm.repository;

import jakarta.transaction.Transactional;
import org.skyhigh.msskyhighrmm.model.DBEntities.AdministratorKeyCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AdministratorKeyCodeRepository extends JpaRepository<AdministratorKeyCodeEntity, UUID> {
    @Query(value = "SELECT * FROM administrator_key_code WHERE key_code_value = ?1", nativeQuery = true)
    List<AdministratorKeyCodeEntity> findByCode(String adminCode);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE administrator_key_code SET user_id = ?2 WHERE id = ?1", nativeQuery = true)
    void setRegisteredUserId(UUID adminId, UUID userId);

    @Query(value = "SELECT * FROM administrator_key_code WHERE user_id = ?1", nativeQuery = true)
    List<AdministratorKeyCodeEntity> findByUserId(UUID userId);
}
