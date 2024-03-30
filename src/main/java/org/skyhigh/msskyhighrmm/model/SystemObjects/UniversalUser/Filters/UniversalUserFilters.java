package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters.UserEntityToUserBOConverter;
import org.skyhigh.msskyhighrmm.repository.UniversalUserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

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
        ArrayList<UniversalUser> result = new ArrayList<>();

        if (block_reason_id == null && userInfoFilters != null &&
                userInfoFilters.getFirstName() == null && userInfoFilters.getSecondName() == null &&
                userInfoFilters.getAge() == 0)
            return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(universalUserRepository.findAll());

        if (userInfoFilters == null && block_reason_id == null)
            return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(universalUserRepository.findAll());

        //HashSet<UniversalUser> resultSet = new HashSet<>();

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

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (block_reason_id != null && filter_first_name != null && filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstNameANDSecondName(
                        block_reason_id,
                        filter_first_name,
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);*/

            } else if (filter_first_name != null && filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstNameANDSecondNameANDAge(
                        filter_first_name,
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (block_reason_id != null && filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDSecondNameANDAge(
                        block_reason_id,
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (block_reason_id != null && filter_first_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstNameANDAge(
                        block_reason_id,
                        filter_first_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (block_reason_id != null && filter_first_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDFirstName(
                        block_reason_id,
                        filter_first_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name))
                        resultSet.add(user);*/

            } else if (block_reason_id != null && filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDSecondName(
                        block_reason_id,
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);*/

            } else if (block_reason_id != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonIdANDAge(
                        block_reason_id,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (filter_first_name != null && filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstNameANDSecondName(
                        filter_first_name,
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);*/

            } else if (filter_first_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstNameANDAge(
                        filter_first_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (filter_second_name != null && filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findBySecondNameANDAge(
                        filter_second_name,
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/

            } else if (filter_first_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByFirstName(
                        filter_first_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name))
                        resultSet.add(user);*/

            } else if (filter_second_name != null) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findBySecondName(
                        filter_second_name
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);*/

            } else if (filter_age != 0) {
                List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByAge(
                        filter_age
                );

                return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);

                /*for (UniversalUser user : usersList)
                    if (user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);*/
            }
        } else {
            List<UniversalUserEntity> usersFromDatabase = universalUserRepository.findByBlockReasonId(
                    block_reason_id
            );

            return (ArrayList<UniversalUser>) UserEntityToUserBOConverter.convertList(usersFromDatabase);
            /*for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id))
                        resultSet.add(user);*/
        }

        return null;
    }
}
