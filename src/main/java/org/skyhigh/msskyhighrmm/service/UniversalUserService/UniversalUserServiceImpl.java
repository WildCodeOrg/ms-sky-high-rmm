package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UsersToBlockInfoListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessageListElement;
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
    public BlockUsersResultMessage blockUsers(ArrayList<UsersToBlockInfoListElement> usersInfoToBlock,
                                              UUID userToBlockId, String blockReasonId) {
        BlockUsersResultMessage resultMessage = new BlockUsersResultMessage(
                null,
                0,
                null);

        //если указаны данные конкретного пользователя и список пользователей одновременно
        if ((userToBlockId != null || blockReasonId != null) && usersInfoToBlock != null) {
            resultMessage.setGlobalOperationCode(2);
            resultMessage.setGlobalMessage("Указание списка пользователей и" +
                    " конкретного пользователя в разных параметрах одновременно запрещено");
            return resultMessage;
        }

        //если не указан идентификатор блокируемого пользователя
        if (userToBlockId == null && blockReasonId != null) {
            resultMessage.setGlobalOperationCode(2);
            resultMessage.setGlobalMessage("Не указан идентификатор блокируемого пользователя");
            return resultMessage;
        }

        //если не указан код причины блокировки
        if (userToBlockId != null && blockReasonId == null) {
            resultMessage.setGlobalOperationCode(2);
            resultMessage.setGlobalMessage("Не указан код причины блокировки");
            return resultMessage;
        }

        //если не указан ни конкретный пользователь, ни список пользователей
        if (userToBlockId == null && usersInfoToBlock == null) {
            resultMessage.setGlobalOperationCode(2);
            resultMessage.setGlobalMessage("Недостаточно информации для блокировки пользователей");
            return resultMessage;
        }

        ArrayList<BlockUsersResultMessageListElement> certainUserBlockResultList = new ArrayList<>();

        //Если указан конкретный пользователь
        if (userToBlockId != null) {
            if (getUserById(userToBlockId) == null) {
                if (resultMessage.getGlobalOperationCode() == 0)
                    resultMessage.setGlobalOperationCode(2);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                userToBlockId + "' не найден",
                        1
                ));
            } //else if () - необходимо добавить проверку наличия причины блокировки с указанным кодом
            else {
                UniversalUser temp = UNIVERSAL_USER_MAP.get(userToBlockId);
                temp.setBlock_reason_id(blockReasonId);
                UNIVERSAL_USER_MAP.replace(userToBlockId, temp);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                userToBlockId + "' успешно заблокирован",
                        0
                ));
            }

            switch (resultMessage.getGlobalOperationCode()) {
                case 0 -> resultMessage.setGlobalMessage("Все пользователи из списка успешно заблокированы");
                case 2 -> resultMessage.setGlobalMessage("Указанный пользователь не заблокирован из-за ошибки");
            }

            resultMessage.setCertainBlockUsersResults(certainUserBlockResultList);

            return resultMessage;
        }

        //если указан список пользователей (и не указан конкретный пользователь)
        for (UsersToBlockInfoListElement element : usersInfoToBlock) {
            if (getUserById(element.getUserId()) == null) {
                if (resultMessage.getGlobalOperationCode() == 0)
                    resultMessage.setGlobalOperationCode(2);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                element.getUserId() + "' не найден",
                        1
                ));
            } //else if () - необходимо добавить проверку наличия причины блокировки с указанным кодом
            else {
                if (resultMessage.getGlobalOperationCode() == 2)
                    resultMessage.setGlobalOperationCode(1);

                UniversalUser temp = UNIVERSAL_USER_MAP.get(element.getUserId());
                temp.setBlock_reason_id(element.getBlockReasonId());
                UNIVERSAL_USER_MAP.replace(element.getUserId(), temp);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                element.getUserId() + "' успешно заблокирован",
                        0
                ));
            }
        }

        switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> resultMessage.setGlobalMessage("Все пользователи из списка заблокированы");
            case 1 -> resultMessage.setGlobalMessage("Заблокирована лишь часть пользователей из списка");
            case 2 -> resultMessage.setGlobalMessage("Ни один пользователь из списка не был заблокирован");
        }

        resultMessage.setCertainBlockUsersResults(certainUserBlockResultList);

        return resultMessage;
    }

    @Override
    public List<UniversalUser> readAll() {
        return new ArrayList<>(UNIVERSAL_USER_MAP.values());
    }
}
