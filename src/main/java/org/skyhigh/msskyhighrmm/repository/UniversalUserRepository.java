package org.skyhigh.msskyhighrmm.repository;

import jakarta.transaction.Transactional;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UniversalUserRepository extends JpaRepository<UniversalUserEntity, UUID> {

    //поиск по логину
    List<UniversalUserEntity> findByLogin(String login);

    //поиск по причине блокировки + имени + фамилии + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?2 AND user_info ->> 'secondName' = ?3 " +
            "AND user_info ->> 'age' = ?4", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDFirstNameANDSecondNameANDAge(
            String block_reason_id,
            String firstName,
            String secondName,
            int age
    );

    //поиск по причине блокировки + имени + фамилии
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?2 AND user_info ->> 'secondName' = ?3", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDFirstNameANDSecondName(
            String block_reason_id,
            String firstName,
            String secondName
    );

    //поиск по имени + фамилии + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?1 AND user_info ->> 'secondName' = ?2 " +
            "AND user_info ->> 'age' = ?3", nativeQuery = true)
    List<UniversalUserEntity> findByFirstNameANDSecondNameANDAge(
            String firstName,
            String secondName,
            int age
    );

    //поиск по причине блокировки + фамилии + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'secondName' = ?2 " +
            "AND user_info ->> 'age' = ?3", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDSecondNameANDAge(
            String block_reason_id,
            String secondName,
            int age
    );

    //поиск по причине блокировки + имени + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?2 " +
            "AND user_info ->> 'age' = ?3", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDFirstNameANDAge(
            String block_reason_id,
            String firstName,
            int age
    );

    //поиск по причине блокировки + имени
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?2", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDFirstName(
            String block_reason_id,
            String firstName
    );

    //поиск по причине блокировки + фамилии
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'secondName' = ?2", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDSecondName(
            String block_reason_id,
            String secondName
    );

    //поиск по причине блокировки + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1 AND user_info IS NOT NULL " +
            "AND user_info ->> 'age' = ?2", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonIdANDAge(
            String block_reason_id,
            int age
    );

    //поиск по имени + фамилии
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?1 AND user_info ->> 'secondName' = ?2", nativeQuery = true)
    List<UniversalUserEntity> findByFirstNameANDSecondName(
            String firstName,
            String secondName
    );

    //поиск по имени + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?1 AND user_info ->> 'age' = ?2", nativeQuery = true)
    List<UniversalUserEntity> findByFirstNameANDAge(
            String firstName,
            int age
    );

    //поиск по фамилии + возрасту
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'secondName' = ?1 AND user_info ->> 'age' = ?2", nativeQuery = true)
    List<UniversalUserEntity> findBySecondNameANDAge(
            String secondName,
            int age
    );

    //поиск по имени
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'firstName' = ?1", nativeQuery = true)
    List<UniversalUserEntity> findByFirstName(
            String firstName
    );

    //поиск по фамилии
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'secondName' = ?1", nativeQuery = true)
    List<UniversalUserEntity> findBySecondName(
            String secondName
    );

    //поиск по возрасту
    @Query(value = "SELECT * FROM universal_user WHERE user_info IS NOT NULL " +
            "AND user_info ->> 'age' = ?1", nativeQuery = true)
    List<UniversalUserEntity> findByAge(
            int age
    );

    //поиск по причине блокировки
    @Query(value = "SELECT * FROM universal_user WHERE block_reason_id=?1", nativeQuery = true)
    List<UniversalUserEntity> findByBlockReasonId(
            String block_reason_id
    );

    //изменение информации о пользователе
    @Transactional
    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value = "UPDATE universal_user SET user_info = ?2 WHERE id = ?1", nativeQuery = true)
    void updateUserInfoForUserWithId(UUID userId, UserInfo newUserInfoAttributes);
}
