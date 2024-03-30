package org.skyhigh.msskyhighrmm.repository;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUserForEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UniversalUserRepository extends JpaRepository<UniversalUserEntity, UUID> {
    List<UniversalUserEntity> findByLogin(String login);
}
