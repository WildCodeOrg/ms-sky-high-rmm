package org.skyhigh.msskyhighrmm.repository;

import jakarta.transaction.Transactional;
import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RolesOperationsRepository extends JpaRepository<RolesOperationsEntity, UUID> {

}
