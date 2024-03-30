package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.AdministratorKeyCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdministratorKeyCodeRepository extends JpaRepository<AdministratorKeyCodeEntity, UUID> {
}
