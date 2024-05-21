package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters.UserEntityToUserBOConverter;
import org.skyhigh.msskyhighrmm.repository.UniversalUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UniversalUserFilters {
    private String block_reason_id;
    private UserInfo user_info;


    public static ArrayList<UniversalUser> filter(String block_reason_id,
                                                  UserInfo userInfoFilters,
                                                  UniversalUserRepository universalUserRepository)
    {
        if (block_reason_id == null && userInfoFilters != null &&
                userInfoFilters.getFirstName() == null && userInfoFilters.getSecondName() == null &&
                userInfoFilters.getAge() == 0)
            return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(universalUserRepository.findAll());

        if (userInfoFilters == null && block_reason_id == null)
            return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(universalUserRepository.findAll());

        if (userInfoFilters != null) {
            final String filter_first_name = userInfoFilters.getFirstName();
            final String filter_second_name = userInfoFilters.getSecondName();
            final int filter_age = userInfoFilters.getAge();

            if (block_reason_id != null && filter_first_name != null && filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstNameANDSecondNameANDAge(
                        block_reason_id,
                        filter_first_name,
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (block_reason_id != null && filter_first_name != null && filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstNameANDSecondName(
                        block_reason_id,
                        filter_first_name,
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_first_name != null && filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstNameANDSecondNameANDAge(
                        filter_first_name,
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (block_reason_id != null && filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDSecondNameANDAge(
                        block_reason_id,
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (block_reason_id != null && filter_first_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstNameANDAge(
                        block_reason_id,
                        filter_first_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (block_reason_id != null && filter_first_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstName(
                        block_reason_id,
                        filter_first_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (block_reason_id != null && filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDSecondName(
                        block_reason_id,
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (block_reason_id != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDAge(
                        block_reason_id,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_first_name != null && filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstNameANDSecondName(
                        filter_first_name,
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_first_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstNameANDAge(
                        filter_first_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findBySecondNameANDAge(
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_first_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstName(
                        filter_first_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findBySecondName(
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

            } else if (filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByAge(
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);
            }
        } else {
            List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonId(
                    block_reason_id
            );

            return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) ||
                (block_reason_id == null ?  ((UniversalUserFilters) obj).block_reason_id == null : this.block_reason_id.equals(((UniversalUserFilters) obj).getBlock_reason_id()))
                && (user_info.getFirstName() == null ? ((UniversalUserFilters) obj).user_info.getFirstName() == null : this.user_info.getFirstName().equals(((UniversalUserFilters) obj).getUser_info().getFirstName()))
                && (user_info.getSecondName() == null ? ((UniversalUserFilters) obj).user_info.getSecondName() == null : this.user_info.getSecondName().equals(((UniversalUserFilters) obj).getUser_info().getSecondName()))
                && this.user_info.getAge() == ((UniversalUserFilters) obj).getUser_info().getAge();
    }
}
