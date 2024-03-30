package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.BlockReason;
import org.skyhigh.msskyhighrmm.model.DBEntities.BlockReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockReasonsRepository extends JpaRepository<BlockReasonEntity, String> {
}
