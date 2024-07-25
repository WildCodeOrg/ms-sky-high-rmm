package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.SecretEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SecretRepository extends JpaRepository<SecretEntity, UUID> {
    @Query(value = "SELECT * FROM secret where login = ?1", nativeQuery = true)
    List<SecretEntity> findByLogin(String login);
}
