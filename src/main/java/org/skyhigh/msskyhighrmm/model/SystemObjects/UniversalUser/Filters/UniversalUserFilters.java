package org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UniversalUserFilters {
    private UUID block_reason_id;
    private UserInfo user_info;


    public static ArrayList<UniversalUser> filter(ArrayList<UniversalUser> usersList,
                                                  UUID block_reason_id,
                                                  UserInfo userInfoFilters)
    {
        if (block_reason_id == null && userInfoFilters != null &&
                userInfoFilters.getFirstName() == null && userInfoFilters.getSecondName() == null &&
                userInfoFilters.getAge() == 0)
            return usersList;

        HashSet<UniversalUser> resultSet = new HashSet<>();

        if (userInfoFilters != null) {
            final String filter_first_name = userInfoFilters.getFirstName();
            final String filter_second_name = userInfoFilters.getSecondName();
            final int filter_age = userInfoFilters.getAge();

            if (block_reason_id != null && filter_first_name != null && filter_second_name != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (block_reason_id != null && filter_first_name != null && filter_second_name != null) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);

            } else if (filter_first_name != null && filter_second_name != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (block_reason_id != null && filter_second_name != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (block_reason_id != null && filter_first_name != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (block_reason_id != null && filter_first_name != null) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getFirstName().equals(filter_first_name))
                        resultSet.add(user);

            } else if (block_reason_id != null && filter_second_name != null) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);

            } else if (block_reason_id != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (filter_first_name != null && filter_second_name != null) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);

            } else if (filter_first_name != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (filter_second_name != null && filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getSecondName().equals(filter_second_name) &&
                            user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);

            } else if (filter_first_name != null) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getFirstName().equals(filter_first_name))
                        resultSet.add(user);

            } else if (filter_second_name != null) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getSecondName().equals(filter_second_name))
                        resultSet.add(user);

            } else if (filter_age != 0) {
                for (UniversalUser user : usersList)
                    if (user.getUser_info().getAge() == filter_age)
                        resultSet.add(user);
            }
        } else {
            if (block_reason_id != null)
                for (UniversalUser user : usersList)
                    if (user.getBlock_reason_id().equals(block_reason_id))
                        resultSet.add(user);
        }

        return new ArrayList<>(resultSet);
    }
}
