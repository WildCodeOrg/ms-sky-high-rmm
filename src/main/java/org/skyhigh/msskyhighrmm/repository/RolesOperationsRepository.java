package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolesOperationsRepository extends JpaRepository<RolesOperationsEntity, UUID> {



}
