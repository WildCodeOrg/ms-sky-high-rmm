package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.BlockReason;
import org.skyhigh.msskyhighrmm.model.DBEntities.BlockReasonEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockReasonsRepository extends JpaRepository<BlockReasonEntity, String> {
    List<BlockReasonEntity> findByDescription(String description);
}
