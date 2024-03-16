package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginatedObject;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversalUserServiceImpl implements UniversalUserService {
    private static final Map<UUID, UniversalUser> UNIVERSAL_USER_MAP = new HashMap<>();

    @Override
    public UUID registerUser(String login, String password) {

        boolean isUserExisting = false;

        for (UniversalUser user : UNIVERSAL_USER_MAP.values())
            if (Objects.equals(user.getLogin(), login)) {
                isUserExisting = true;
                break;
            }

        if (!isUserExisting) {
            UUID universal_user_id = UUID.randomUUID();

            while (UNIVERSAL_USER_MAP.get(universal_user_id) != null) {
                universal_user_id = UUID.randomUUID();
            }

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
    public ListOfUniversalUser searchUsers(PaginationInfo paginationInfo, UniversalUserFilters universalUserFilters, UniversalUserSort universalUserSort) {
        ArrayList<UniversalUser> temporaryAllUsersList = new ArrayList<>(UNIVERSAL_USER_MAP.values());
        ArrayList<UniversalUser> resultUniversalUsersList;

        if (universalUserFilters != null) {
            temporaryAllUsersList = UniversalUserFilters.filter(temporaryAllUsersList,
                    universalUserFilters.getBlock_reason_id(), universalUserFilters.getUser_info());
        }

        if (universalUserSort != null) {
            UniversalUserSort.sort(temporaryAllUsersList, universalUserSort);
        }

        int paginationItemCount = temporaryAllUsersList.size();
        int paginationPageNumber = 1;
        int itemCount = temporaryAllUsersList.size();

        if (paginationInfo != null) {
            paginationItemCount = paginationInfo.getRequestedItemCount();
            paginationPageNumber = paginationInfo.getPage();

            PaginatedObject<UniversalUser> paginated = new PaginatedObject<>(paginationItemCount,
                    paginationPageNumber, temporaryAllUsersList);
            resultUniversalUsersList = paginated.getResultList();
        } else {
            resultUniversalUsersList = new ArrayList<>(temporaryAllUsersList);
        }

        return resultUniversalUsersList != null
            ? new ListOfUniversalUser(itemCount, paginationItemCount, paginationPageNumber, resultUniversalUsersList)
            : null;
    }

    @Override
    public UniversalUser updateUserById(UUID userId, UserInfo newUserInfoAttributes) {
        UniversalUser foundUniversalUser = UNIVERSAL_USER_MAP.get(userId);
        UNIVERSAL_USER_MAP.put(userId, new UniversalUser(userId, foundUniversalUser.getLogin(),
                foundUniversalUser.getPassword(), newUserInfoAttributes, foundUniversalUser.getBlock_reason_id()));

        return UNIVERSAL_USER_MAP.get(userId);
    }

    @Override
    public List<UniversalUser> readAll() {
        return new ArrayList<>(UNIVERSAL_USER_MAP.values());
    }
}
