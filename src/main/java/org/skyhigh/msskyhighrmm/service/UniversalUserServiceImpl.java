package org.skyhigh.msskyhighrmm.service;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.DTO.registerUserDTOs.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Comparators.UniversalUser.LoginUniversalUserComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Comparators.UniversalUser.UserIdUniversalUserComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Filters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUserSearchSort.SortParameters.UniversalUserSortParameter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUserSearchSort.UniversalUserSort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversalUserServiceImpl implements UniversalUserService {
    private static final Map<UUID, UniversalUser> UNIVERSAL_USER_MAP = new HashMap<>();

    @Override
    public UUID registerUser(DeliveryRequestRegisterUserDTO registeringUniversalUser) {
        ArrayList<UniversalUser> universalUsers = new ArrayList<>(UNIVERSAL_USER_MAP.values());
        boolean isUserExisting = false;

        for (UniversalUser user : universalUsers)
            if (Objects.equals(user.getLogin(), registeringUniversalUser.getLogin())) {
                isUserExisting = true;
                break;
            }

        if (!isUserExisting) {
            final UUID universal_user_id = UUID.randomUUID();
            UniversalUser universalUser = new UniversalUser(universal_user_id, registeringUniversalUser.getLogin(),
                    registeringUniversalUser.getPassword(), null, null);
            UNIVERSAL_USER_MAP.put(universal_user_id, universalUser);
            return universal_user_id;
        } else {
            return null;
        }
    }

    @Override
    public UUID checkUser(String login) {
        ArrayList<UniversalUser> universalUsers = new ArrayList<>(UNIVERSAL_USER_MAP.values());
        UUID id = null;

        for (UniversalUser user : universalUsers)
            if (Objects.equals(user.getLogin(), login)) {
                id = user.getId();
                break;
            }

        return id;
    }

    @Override
    public UniversalUser getUserById(UUID id) {
        return UNIVERSAL_USER_MAP.get(id);
    }

    @Override
    public ListOfUniversalUser searchUsers(Pagination pagination, Filters filters, UniversalUserSort universalUserSort) {
        ArrayList<UniversalUser> temporaryAllUsersList = new ArrayList<>(UNIVERSAL_USER_MAP.values());

        if (filters != null) {
            deleteInappropriateElementsOfUserListByParams(temporaryAllUsersList,
                    filters.getBlock_reason_id(), filters.getUser_info());
        }

        if (universalUserSort != null) {
            switch (universalUserSort.getDirection()) {
                case ASC -> {
                    if (universalUserSort.getSortBy() == UniversalUserSortParameter.LOGIN) {
                        temporaryAllUsersList.sort(new LoginUniversalUserComparator());
                    } else if (universalUserSort.getSortBy() == UniversalUserSortParameter.USER_ID) {
                        temporaryAllUsersList.sort(new UserIdUniversalUserComparator());
                    }
                }
                case DESC -> {
                    if (universalUserSort.getSortBy() == UniversalUserSortParameter.LOGIN) {
                        temporaryAllUsersList.sort(new LoginUniversalUserComparator().reversed());
                    } else if (universalUserSort.getSortBy() == UniversalUserSortParameter.USER_ID) {
                        temporaryAllUsersList.sort(new UserIdUniversalUserComparator().reversed());
                    }
                }
            }
        }

        int paginationItemCount = pagination.getRequestedItemCount();
        int paginationPageNumber = pagination.getPage();
        int firstElementOfResultListPosition = ((paginationPageNumber - 1) * paginationItemCount);
        int itemCount = temporaryAllUsersList.size();

        if (firstElementOfResultListPosition >= temporaryAllUsersList.size()
                || paginationItemCount <= 0 || paginationPageNumber <= 0)
            return null;

        ArrayList<UniversalUser> resultUniversalUsersList = new ArrayList<>();

        for (int i = firstElementOfResultListPosition;
             i < firstElementOfResultListPosition + paginationItemCount; i++)
        {
            resultUniversalUsersList.add(temporaryAllUsersList.get(i));
            if (i == temporaryAllUsersList.size() - 1) break;
        }

        return new ListOfUniversalUser(itemCount, paginationItemCount, paginationPageNumber, resultUniversalUsersList);
    }

    void deleteInappropriateElementsOfUserListByParams(ArrayList<UniversalUser> usersList, UUID block_reason_id,
                                                       UserInfo userInfoFilters)
    {
        HashSet<Integer> toDeleteElementPositionsSet = new HashSet<>();

        if (block_reason_id != null) {
            for (int i = 0; i < usersList.size(); i++) {
                if (!usersList.get(i).getBlock_reason_id().equals(block_reason_id)) {
                    toDeleteElementPositionsSet.add(i);
                }
            }
        }

        if (userInfoFilters != null) {
            final String filer_first_name = userInfoFilters.getFirstName();
            final String filter_second_name = userInfoFilters.getSecondName();
            final int filter_age = userInfoFilters.getAge();

            if (filer_first_name != null) {
                for (int i = 0; i < usersList.size(); i++) {
                    if (!usersList.get(i).getUser_info().getFirstName().equals(filer_first_name)) {
                        toDeleteElementPositionsSet.add(i);
                    }
                }
            }

            if (filter_second_name != null) {
                for (int i = 0; i < usersList.size(); i++) {
                    if (!usersList.get(i).getUser_info().getSecondName().equals(filter_second_name)) {
                        toDeleteElementPositionsSet.add(i);
                    }
                }
            }

            if (filter_age != 0) {
                for (int i = 0; i < usersList.size(); i++) {
                    if (usersList.get(i).getUser_info().getAge() != filter_age) {
                        toDeleteElementPositionsSet.add(i);
                    }
                }
            }
        }

        int[] toDeleteElementPositionsList = new int[toDeleteElementPositionsSet.size()];
        int iter = 0;
        for (Integer toDeleteElementPosition : toDeleteElementPositionsSet) {
            toDeleteElementPositionsList[iter] = toDeleteElementPosition;
            ++iter;
            //usersList.remove((int) toDeleteElementPosition);
        }
        for (int i = 0; i < toDeleteElementPositionsList.length; i++) {
            usersList.remove(toDeleteElementPositionsList[i]);
            if (i != toDeleteElementPositionsList.length - 1)
                for (int j = i + 1; j < toDeleteElementPositionsList.length; j++) {
                    toDeleteElementPositionsList[j] -= 1;
                }
        }
    }

    @Override
    public void create(UniversalUser universal_user) {
        final UUID universal_user_id = UUID.randomUUID();
        universal_user.setId(universal_user_id);
        UNIVERSAL_USER_MAP.put(universal_user_id, universal_user);
    }

    @Override
    public List<UniversalUser> readAll() {
        return new ArrayList<>(UNIVERSAL_USER_MAP.values());
    }

    @Override
    public boolean update(UniversalUser universal_user, UUID id) {
        if (UNIVERSAL_USER_MAP.containsKey(id)) {
            universal_user.setId(id);
            UNIVERSAL_USER_MAP.put(id, universal_user);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return UNIVERSAL_USER_MAP.remove(id) != null;
    }
}
