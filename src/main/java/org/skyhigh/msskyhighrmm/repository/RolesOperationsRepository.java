package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.RolesOperations.RoleOperation;
import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RolesOperationsRepository extends JpaRepository<RolesOperationsEntity, UUID> {

}
