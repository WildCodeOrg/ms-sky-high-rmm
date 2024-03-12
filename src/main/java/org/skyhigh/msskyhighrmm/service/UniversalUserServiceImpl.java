package org.skyhigh.msskyhighrmm.service;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Comparators.LoginUniversalUserComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Comparators.UserIdUniversalUserComparator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSortParameter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversalUserServiceImpl implements UniversalUserService {
    private static final Map<UUID, UniversalUser> UNIVERSAL_USER_MAP = new HashMap<>();

    @Override
    public UUID registerUser(String login, String password) {
        ArrayList<UniversalUser> universalUsers = new ArrayList<>(UNIVERSAL_USER_MAP.values());

        boolean isUserExisting = false;

        for (UniversalUser user : universalUsers)
            if (Objects.equals(user.getLogin(), login)) {
                isUserExisting = true;
                break;
            }

        if (!isUserExisting) {
            final UUID universal_user_id = UUID.randomUUID();
            UniversalUser universalUser = new UniversalUser(universal_user_id, login,
                    password, null, null);
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
    public ListOfUniversalUser searchUsers(Pagination pagination, UniversalUserFilters universalUserFilters, UniversalUserSort universalUserSort) {
        ArrayList<UniversalUser> temporaryAllUsersList = new ArrayList<>(UNIVERSAL_USER_MAP.values());

        if (universalUserFilters != null) {
            temporaryAllUsersList = UniversalUserFilters.filter(temporaryAllUsersList,
                    universalUserFilters.getBlock_reason_id(), universalUserFilters.getUser_info());
        }

        if (universalUserSort != null) {
            UniversalUserSort.sort(temporaryAllUsersList, universalUserSort);
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
