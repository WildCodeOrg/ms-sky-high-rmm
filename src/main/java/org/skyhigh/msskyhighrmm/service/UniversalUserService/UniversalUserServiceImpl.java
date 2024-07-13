package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UsersToBlockInfoListElement;
import org.skyhigh.msskyhighrmm.model.DBEntities.*;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions.UnassignPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions.UnassignPermissionsResultMessageListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddAdminKey.AddAdminKeyResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddBlockReason.AddBlockReasonResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddPermission.UserAddPermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddPermission.UserAddPermissionResultMessageListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddRoleToUser.AddRoleToUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddRoleToUser.AddRoleToUserResultMessageListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessageListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserPermission.GetUserPermissionForceAssignedPermission;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserPermission.GetUserPermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles.GetUserRolesListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles.GetUserRolesResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser.LoginUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RegisterUser.RegisterUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList.RemoveRoleFromUserListResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList.RemoveRoleFromUserListResultMessageListElement;
import org.skyhigh.msskyhighrmm.model.SystemObjects.DateTimeFormatter.DateTimeFormatter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator.StringEnumValidator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.StringEnumValidator.ValidatingEnums.UserPermissionFilter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginatedObject;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Converters.UserEntityToUserBOConverter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;
import org.skyhigh.msskyhighrmm.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class UniversalUserServiceImpl implements UniversalUserService {
    private final UniversalUserRepository universalUserRepository;
    private final BlockReasonsRepository blockReasonsRepository;
    private final AdministratorKeyCodeRepository administratorKeyCodeRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final UserGroupRolesRepository usersGroupRolesRepository;
    private final OperationPermissionsRepository operationPermissionsRepository;
    private final UserPermissionRepository userPermissionRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger log = Logger.getLogger(UniversalUserServiceImpl.class.getName());

    public UniversalUserServiceImpl(UniversalUserRepository universalUserRepository,
                                    BlockReasonsRepository blockReasonsRepository,
                                    AdministratorKeyCodeRepository administratorKeyCodeRepository,
                                    UsersRolesRepository usersRolesRepository,
                                    UserGroupRolesRepository usersGroupRolesRepository,
                                    OperationPermissionsRepository operationPermissionsRepository,
                                    PasswordEncoder passwordEncoder, UserPermissionRepository userPermissionRepository) {
        this.universalUserRepository = universalUserRepository;
        this.blockReasonsRepository = blockReasonsRepository;
        this.administratorKeyCodeRepository = administratorKeyCodeRepository;
        this.usersRolesRepository = usersRolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersGroupRolesRepository = usersGroupRolesRepository;
        this.operationPermissionsRepository = operationPermissionsRepository;
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    public RegisterUserResultMessage registerUser(String login, String password, boolean isAdmin, String adminKey) {
        List<AdministratorKeyCodeEntity> adminKeyCodeEntities = new ArrayList<>();

        if (!(6 <= login.length() && login.length() <= 20)) {
            return new RegisterUserResultMessage(
                    "Login length must be in the range from 6 to 20 characters.",
                    1,
                    null
            );
        }

        if (!(8 <= password.length() && password.length() <= 20)) {
            return new RegisterUserResultMessage(
                    "Password length must be in the range from 8 to 20 characters.",
                    2,
                    null
            );
        }

        if (isAdmin) {
            if (adminKey == null)
                return new RegisterUserResultMessage(
                        "AdminKey cannot be null for admins.",
                        4,
                        null
                );

            if (adminKey.length() != 32)
                return new RegisterUserResultMessage(
                        "AdminKey must be size of 32.",
                        5,
                        null
                );

            adminKeyCodeEntities =
                    administratorKeyCodeRepository.findByCode(adminKey);

            if (adminKeyCodeEntities == null || adminKeyCodeEntities.size() != 1)
                return new RegisterUserResultMessage(
                        "There is no such admin key code for admins.",
                        6,
                        null
                );

            if (adminKeyCodeEntities.get(0).getUser_id() != null)
                return new RegisterUserResultMessage(
                        "Admin with certain admin key code already exists.",
                        7,
                        null
                );
        }

        if (!universalUserRepository.findByLogin(login).isEmpty())
            return new RegisterUserResultMessage(
                    "User with this id already exists.",
                    3,
                    null
            );

        String encodedPass = passwordEncoder.encode(password);

        UniversalUserEntity user = new UniversalUserEntity(
                null,
                login,
                encodedPass,
                null,
                null
        );

        UUID universal_user_id = (universalUserRepository.save(user)).getId();

        if (isAdmin) {
            administratorKeyCodeRepository.setRegisteredUserId(
                    adminKeyCodeEntities.get(0).getId(),
                    universal_user_id
            );
        }

        return new RegisterUserResultMessage(
                "User created successfully.",
                0,
                universal_user_id
        );
    }

    @Override
    public LoginUserResultMessage loginUser(String login, String password) {
        LoginUserResultMessage result = new LoginUserResultMessage();

        ArrayList<UniversalUserEntity> users = (ArrayList<UniversalUserEntity>) universalUserRepository
                .findByLogin(login);

        if (users.size() != 1) {
            result.setGlobalOperationCode(1);
            result.setGlobalMessage("Пользователя не существует.");
            return result;
        }

        if (!passwordEncoder.matches(password, users.get(0).getPassword())) {
            result.setGlobalOperationCode(2);
            result.setGlobalMessage("Неправильный пароль.");
            return result;
        }

        result.setGlobalOperationCode(0);
        result.setLogonUserId(users.get(0).getId());
        return result;
    }

    //Проверка существования пользователя с указанным логином
    @Override
    public UUID checkUser(String login) {
        ArrayList<UniversalUserEntity> users = (ArrayList<UniversalUserEntity>) universalUserRepository
                .findByLogin(login);

        if (users.size() != 1)
            return null;

        return users.get(0).getId();
    }

    @Override
    public UniversalUser getUserById(UUID id) {
        Optional<UniversalUserEntity> user = universalUserRepository.findById(id);
        return user.map(UserEntityToUserBOConverter::convert).orElse(null);
    }

    @Override
    public ListOfUniversalUser searchUsers(PaginationInfo paginationInfo, UniversalUserFilters universalUserFilters, UniversalUserSort universalUserSort) {
        ArrayList<UniversalUser> resultUniversalUsersList = (ArrayList<UniversalUser>) UserEntityToUserBOConverter
                .convertList(
                    universalUserRepository.findAll()
                );

        if (universalUserFilters != null) {
            resultUniversalUsersList = UniversalUserFilters.filter(universalUserFilters.getBlock_reason_id(),
                    universalUserFilters.getUser_info(),
                    universalUserRepository);
        }

        if (resultUniversalUsersList == null) return null;

        if (universalUserSort != null) {
            UniversalUserSort.sort(resultUniversalUsersList, universalUserSort);
        }

        int paginationItemCount = resultUniversalUsersList.size();
        int paginationPageNumber = 1;
        int itemCount = resultUniversalUsersList.size();

        if (paginationInfo != null) {
            paginationItemCount = paginationInfo.getRequestedItemCount();
            paginationPageNumber = paginationInfo.getPage();

            PaginatedObject<UniversalUser> paginated = new PaginatedObject<>(paginationItemCount,
                    paginationPageNumber, resultUniversalUsersList);
            resultUniversalUsersList = paginated.getResultList();
        }

        return resultUniversalUsersList != null
            ? new ListOfUniversalUser(itemCount, paginationItemCount, paginationPageNumber, resultUniversalUsersList)
            : null;
    }

    @Override
    public UniversalUser updateUserById(UUID userId, UserInfo newUserInfoAttributes) {
        if (!universalUserRepository.existsById(userId)) return null;

        universalUserRepository.updateUserInfoForUserWithId(userId, newUserInfoAttributes);

        Optional<UniversalUserEntity> user = universalUserRepository.findById(userId);
        return user.map(UserEntityToUserBOConverter::convert).orElse(null);
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
            if (!universalUserRepository.existsById(userToBlockId)) {
                if (resultMessage.getGlobalOperationCode() == 0)
                    resultMessage.setGlobalOperationCode(2);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                userToBlockId + "' не найден",
                        1
                ));
            }
            else if (!blockReasonsRepository.existsById(blockReasonId)) {
                if (resultMessage.getGlobalOperationCode() == 0)
                    resultMessage.setGlobalOperationCode(2);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Причина блокировки с идентификатором '" +
                                blockReasonId + "' не найдена",
                        2
                ));
            } else {
                universalUserRepository.setBlockReasonIdForUserWithId(userToBlockId, blockReasonId);

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                userToBlockId + "' успешно заблокирован",
                        0
                ));
            }

            switch (resultMessage.getGlobalOperationCode()) {
                case 0 -> resultMessage.setGlobalMessage("Указанный пользователь успешно заблокированы");
                case 2 -> resultMessage.setGlobalMessage("Указанный пользователь не заблокирован из-за ошибки");
            }

            resultMessage.setCertainBlockUsersResults(certainUserBlockResultList);

            return resultMessage;
        }

        //если указан список пользователей (и не указан конкретный пользователь)
        for (UsersToBlockInfoListElement element : usersInfoToBlock) {
            if (!universalUserRepository.existsById(element.getUserId())) {
                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                element.getUserId() + "' не найден",
                        1
                ));
            }
            else if (!blockReasonsRepository.existsById(element.getBlockReasonId())) {
                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Причина блокировки с идентификатором '" +
                                element.getBlockReasonId() + "' не найдена",
                        2
                ));
            }
            else {
                universalUserRepository.setBlockReasonIdForUserWithId(
                        element.getUserId(),
                        element.getBlockReasonId()
                );

                certainUserBlockResultList.add(new BlockUsersResultMessageListElement(
                        "Пользователь с идентификатором '" +
                                element.getUserId() + "' успешно заблокирован",
                        0
                ));
            }
        }

        int unsuccessfullyBlockAmount = 0;
        for (BlockUsersResultMessageListElement certainUserBlockResultListElement : certainUserBlockResultList) {
            if (certainUserBlockResultListElement.getOperationCode() != 0)
                unsuccessfullyBlockAmount++;
        }

        if (unsuccessfullyBlockAmount == certainUserBlockResultList.size())
            resultMessage.setGlobalOperationCode(2);
        else if (unsuccessfullyBlockAmount != 0)
            resultMessage.setGlobalOperationCode(1);

        switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> resultMessage.setGlobalMessage("Все пользователи из списка заблокированы");
            case 1 -> resultMessage.setGlobalMessage("Заблокирована лишь часть пользователей из списка");
            case 2 -> resultMessage.setGlobalMessage("Ни один пользователь из списка не был заблокирован");
        }

        resultMessage.setCertainBlockUsersResults(certainUserBlockResultList);

        return resultMessage;
    }

    @Override
    public AddAdminKeyResultMessage addAdminKey(UUID userMadeRequest, String adminKey) {
        List<AdministratorKeyCodeEntity> checkUserMadeRequestList =
                administratorKeyCodeRepository.findByUserId(userMadeRequest);

        if (checkUserMadeRequestList == null || checkUserMadeRequestList.isEmpty())
            return new AddAdminKeyResultMessage(
                    "User made request is not an administrator.",
                    2,
                    null
            );

        if (adminKey.length() != 32)
            return new AddAdminKeyResultMessage(
                    "Admin key length must be 32 characters.",
                    1,
                    null
            );

        AdministratorKeyCodeEntity administratorKeyCodeReferenceToCreate = new AdministratorKeyCodeEntity(
                null,
                null,
                adminKey
        );

        UUID createdReferenceId = (administratorKeyCodeRepository.save(administratorKeyCodeReferenceToCreate))
                .getId();

        return new AddAdminKeyResultMessage(
                "Admin key created.",
                0,
                createdReferenceId
        );
    }

    @Override
    public AddRoleToUserResultMessage addRoleToUsers(UUID userMadeRequestId, UUID roleId, List<UUID> usersToAddRoleIds) {
        Optional<UserGroupRolesEntity> role = usersGroupRolesRepository.findById(roleId);

        if (!universalUserRepository.existsById(userMadeRequestId))
            return new AddRoleToUserResultMessage(
                    "Пользователь, инициировавший операцию, не найден",
                    3,
                    null
            );

        if (role.isEmpty())
            return new AddRoleToUserResultMessage(
                    "Роль с указанным идентификатором не существует.",
                    4,
                    null
            );

        if (usersToAddRoleIds == null || usersToAddRoleIds.isEmpty())
            return new AddRoleToUserResultMessage(
                    "Список идентификаторов пользователей userToAddRoleIds пуст.",
                    5,
                    null
            );

        AddRoleToUserResultMessage addRoleToUserResultMessage = new AddRoleToUserResultMessage();
        addRoleToUserResultMessage.setGlobalOperationCode(0);
        List<AddRoleToUserResultMessageListElement> roleToUserResultMessageListElements = new ArrayList<>();
        for (UUID userToAddRoleId : usersToAddRoleIds) {
            Optional<UniversalUserEntity> userToAddRole = universalUserRepository.findById(userToAddRoleId);

            if (userToAddRole.isEmpty()) {
                log.info("Adding role with id '" + roleId + "' process log: "
                        + "User with id '" + userToAddRoleId + "' does not exist.");

                roleToUserResultMessageListElements.add(new AddRoleToUserResultMessageListElement(
                        userToAddRoleId,
                        "Пользователь с указанным идентификатором не существует.",
                        1
                ));
            }
            else {
                List<UsersRolesEntity> userRolesList = usersRolesRepository.findByUserId(userToAddRoleId);

                boolean isRoleAlreadyAdded = false;
                for (UsersRolesEntity userRole : userRolesList) {
                    if (userRole.getRole_id().getId().equals(roleId)) {
                        isRoleAlreadyAdded = true;
                        break;
                    }
                }

                if (isRoleAlreadyAdded) {
                    roleToUserResultMessageListElements.add(new AddRoleToUserResultMessageListElement(
                            userToAddRoleId,
                            "Указанная роль уже назначена данному пользователю.",
                            2
                    ));
                } else {
                    UsersRolesEntity usersRolesEntity = new UsersRolesEntity();
                    usersRolesEntity.setUser_id(userToAddRole.get());
                    usersRolesEntity.setRole_id(role.get());

                    UUID createdReferenceId = (usersRolesRepository.save(usersRolesEntity)).getId();
                    log.info("Adding role with id '" + roleId + "' process log: "
                            + "A reference in users_roles table was created with id '" + createdReferenceId
                            + "' for user with id '" + userToAddRoleId + "'");

                    roleToUserResultMessageListElements.add(new AddRoleToUserResultMessageListElement(
                            userToAddRoleId,
                            "Роль успешно добавлена для указанного пользователя.",
                            0
                    ));
                }
            }
        }

        int unsuccessfullyAddRoleAmount = 0;
        for (AddRoleToUserResultMessageListElement roleToUserResultMessageListElement : roleToUserResultMessageListElements) {
            if (roleToUserResultMessageListElement.getOperationCode() != 0)
                unsuccessfullyAddRoleAmount++;
        }

        if (unsuccessfullyAddRoleAmount == roleToUserResultMessageListElements.size()) {
            addRoleToUserResultMessage.setGlobalOperationCode(2);
            addRoleToUserResultMessage.setGlobalMessage("Ни одному из переданных пользователей не была назначена указанная роль.");
        }
        else if (unsuccessfullyAddRoleAmount != 0) {
            addRoleToUserResultMessage.setGlobalOperationCode(1);
            addRoleToUserResultMessage.setGlobalMessage("Роль добавлена успешно лишь части указанных пользователей.");
        } else {
            addRoleToUserResultMessage.setGlobalMessage("Роль была успешно добавлена всем указанным пользователям.");
        }

        addRoleToUserResultMessage.setCertainAddRoleToUsersResults(roleToUserResultMessageListElements);

        return addRoleToUserResultMessage;
    }

    @Override
    public GetUserRolesResultMessage getUserRoles(UUID userMadeRequestId, UUID userId) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new GetUserRolesResultMessage(
                    "Пользователь с id '" + userMadeRequestId + "' не найден.",
                    1,
                    null
            );

        if (!universalUserRepository.existsById(userId))
            return new GetUserRolesResultMessage(
                    "Пользователь с id '" + userId + "' не найден.",
                    2,
                    null
            );

        List<UserGroupRolesEntity> rolesOfUser = usersGroupRolesRepository.getRolesOfUser(userId);
        if (rolesOfUser == null || rolesOfUser.isEmpty())
            return new GetUserRolesResultMessage(
                    "У пользователя с id '" + userId + "' нет назначенных ролей.",
                    3,
                    null
            );

        List<GetUserRolesListElement> resultMessageElement = new ArrayList<>();
        for (UserGroupRolesEntity role : rolesOfUser) {
            resultMessageElement.add(new GetUserRolesListElement(
                    role.getId(),
                    role.getRoleName()
            ));
        }

        return new GetUserRolesResultMessage(
                "Роли пользователя успешно найдены.",
                0,
                resultMessageElement
        );
    }

    @Override
    public RemoveRoleFromUserListResultMessage removeRoleFromUserList(UUID userMadeRequestId, UUID roleId, List<UUID> usersToRemoveRoleIds) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new RemoveRoleFromUserListResultMessage(
                    "Пользователь, инициировавший выполнение операции, не найден.",
                    3,
                    null
            );

        if (!usersGroupRolesRepository.existsById(roleId))
            return new RemoveRoleFromUserListResultMessage(
                    "Роли с указанным id не существует.",
                    4,
                    null
            );

        if (usersToRemoveRoleIds == null || usersToRemoveRoleIds.isEmpty())
            return new RemoveRoleFromUserListResultMessage(
                    "Список идентификаторов userIds пуст.",
                    5,
                    null
            );

        Optional<UserGroupRolesEntity> userGroupRolesEntityOptional = usersGroupRolesRepository.findById(roleId);

        if (userGroupRolesEntityOptional.isEmpty())
            return new RemoveRoleFromUserListResultMessage(
                    "Произошла системная ошибка.",
                    7,
                    null
            );

        if (userGroupRolesEntityOptional.get().isCritical()) {
            Optional<UniversalUserEntity> universalUserEntityOptional = universalUserRepository.findById(userMadeRequestId);

            if (universalUserEntityOptional.isEmpty())
                return new RemoveRoleFromUserListResultMessage(
                        "Произошла системная ошибка.",
                        7,
                        null
                );

            List<AdministratorKeyCodeEntity> userAdminListById = administratorKeyCodeRepository.findByUserId(userMadeRequestId);
            if (userAdminListById == null || userAdminListById.isEmpty())
                return new RemoveRoleFromUserListResultMessage(
                        "Отмену критической роли может выполнять только пользователь с правами администратора.",
                        6,
                        null
                );
        }

        RemoveRoleFromUserListResultMessage resultMessage = new RemoveRoleFromUserListResultMessage();
        resultMessage.setGlobalOperationCode(0);
        List<RemoveRoleFromUserListResultMessageListElement> removeRoleResultList = new ArrayList<>();

        for (UUID userId : usersToRemoveRoleIds) {
            Optional<UniversalUserEntity> userToRemoveRole = universalUserRepository.findById(userId);

            if (userToRemoveRole.isEmpty()) {
                log.info("Removing role with id '" + roleId + "' process log: "
                        + "User with id '" + userId + "' does not exist.");

                removeRoleResultList.add(new RemoveRoleFromUserListResultMessageListElement(
                        userId,
                        "Пользователь с указанным идентификатором не существует.",
                        1
                ));
            }
            else {
                List<UsersRolesEntity> userRolesList = usersRolesRepository.findByUserId(userId);

                UsersRolesEntity usersRolesEntity = null;
                for (UsersRolesEntity userRole : userRolesList) {
                    if (userRole.getRole_id().getId().equals(roleId)) {
                        usersRolesEntity = userRole;
                        break;
                    }
                }

                if (usersRolesEntity == null) {
                    log.info("Removing role with id '" + roleId + "' process log: "
                            + "user with id '" + userId + "' does not have this role.");

                    removeRoleResultList.add(new RemoveRoleFromUserListResultMessageListElement(
                            userId,
                            "У пользователя нет указанной роли.",
                            2
                    ));
                } else {
                    UUID removedUserRoleId = usersRolesEntity.getId();
                    usersRolesRepository.deleteById(removedUserRoleId);

                    log.info("Removing role with id '" + roleId + "' process log: "
                            + "A reference in users_roles table with id '" + removedUserRoleId
                            + "' was removed for user with id '" + userId + "'");

                    removeRoleResultList.add(new RemoveRoleFromUserListResultMessageListElement(
                            userId,
                            "Роль успешно отменена для указанного пользователя.",
                            0
                    ));
                }
            }
        }

        int unsuccessfullyRemoveRoleAmount = 0;
        for (RemoveRoleFromUserListResultMessageListElement removeRoleFromUserListResultMessageListElement : removeRoleResultList) {
            if (removeRoleFromUserListResultMessageListElement.getOperationCode() != 0)
                unsuccessfullyRemoveRoleAmount++;
        }

        if (unsuccessfullyRemoveRoleAmount == removeRoleResultList.size()) {
            resultMessage.setGlobalOperationCode(2);
            resultMessage.setGlobalMessage("Ни одному из переданных пользователей не назначена указанная роль.");
        }
        else if (unsuccessfullyRemoveRoleAmount != 0) {
            resultMessage.setGlobalOperationCode(1);
            resultMessage.setGlobalMessage("Роль отменена успешно лишь для части указанных пользователей.");
        } else {
            resultMessage.setGlobalMessage("Роль была успешно отменена для всех указанных пользователей.");
        }

        resultMessage.setCertainResultMessages(removeRoleResultList);

        return resultMessage;
    }

    @Override
    public AddBlockReasonResultMessage addBlockReason(UUID userMadeRequestId, String blockReasonDescription) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new AddBlockReasonResultMessage(
                    null,
                    1,
                    "Пользователь, инициировавший операцию, не найден"
            );

        BlockReasonEntity blockReasonToSave = new BlockReasonEntity();
        blockReasonToSave.setDescription(blockReasonDescription);

        String createdReferenceId = (blockReasonsRepository.save(blockReasonToSave))
                .getId();

        if (createdReferenceId == null || createdReferenceId.isEmpty())
            return new AddBlockReasonResultMessage(
                    null,
                    2,
                    "Произошла непредвиденная ошибка"
            );

        return new AddBlockReasonResultMessage(
                createdReferenceId,
                0,
                "Причина блокировки успешно сохранена"
        );
    }

    @Override
    public GetUserPermissionResultMessage getUserPermission(UUID userMadeRequestId, UUID userId, String filter) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new GetUserPermissionResultMessage(
                    1,
                    "Пользователь, инициировавший выполнение операции, не найден",
                    null
            );

        if (!universalUserRepository.existsById(userId))
            return new GetUserPermissionResultMessage(
                    2,
                    "Пользователь, для которого выполняется поиск разрешений, не найден",
                    null
            );

        StringEnumValidator<UserPermissionFilter> validator = new StringEnumValidator<>();
        Map<StringEnumValidator.StringEnumValidationResult, String> enumValidationResult = validator.validateAttributeWithMessage(
                UserPermissionFilter.class,
                "Тип фильтрации",
                filter
        );

        if (!enumValidationResult.isEmpty() && enumValidationResult.get(StringEnumValidator.StringEnumValidationResult.SUCCESS) == null)
            return new GetUserPermissionResultMessage(
                    3,
                    "Указан некорректный тип фильтрации. "
                            + enumValidationResult.get(StringEnumValidator.StringEnumValidationResult.FAILURE),
                    null
            );

        Map<String, List<?>> resultPermissions = new HashMap<>();

        return switch (UserPermissionFilter.valueOf(filter)) {
            case ALL -> {
                List<UserPermissionEntity> forceAssignedUserPermissionsRelationshipReferences = userPermissionRepository.findByUserId(userId);
                List<OperationPermissionsEntity> roleBasedUserPermissions = operationPermissionsRepository.findRoleBasedUserPermissionByUserId(userId);
                List<GetUserPermissionForceAssignedPermission> forceAssignedUserPermissions = forceAssignedUserPermissionsRelationshipReferences.stream()
                        .map(x -> new GetUserPermissionForceAssignedPermission(
                                x.getPermissionId(),
                                x.getCreateDate()
                        )
                ).toList();

                if (
                       // (roleBasedUserPermissions == null && forceAssignedUserPermissions == null) ||
                                (roleBasedUserPermissions != null //&& forceAssignedUserPermissions != null
                                && roleBasedUserPermissions.isEmpty() && forceAssignedUserPermissions.isEmpty())
                )
                    yield new GetUserPermissionResultMessage(
                           4,
                           "У пользователя '" + userId + "' нет назначенных разрешений, соответствующих переданному критерию фильтрации",
                           null
                    );

                resultPermissions.put("forcedAssigned", forceAssignedUserPermissions);
                resultPermissions.put("roleBased", roleBasedUserPermissions);
                yield new GetUserPermissionResultMessage(
                        0,
                        "Разрешения пользователя найдены успешно",
                        resultPermissions
                );
            }

            case BY_ROLES -> {
                List<OperationPermissionsEntity> roleBasedUserPermissions = operationPermissionsRepository.findRoleBasedUserPermissionByUserId(userId);
                if (roleBasedUserPermissions == null || roleBasedUserPermissions.isEmpty())
                    yield new GetUserPermissionResultMessage(
                            4,
                            "У пользователя '" + userId + "' нет назначенных разрешений, соответствующих переданному критерию фильтрации",
                            null
                    );

                resultPermissions.put("roleBased", roleBasedUserPermissions);
                yield new GetUserPermissionResultMessage(
                        0,
                        "Разрешения пользователя найдены успешно",
                        resultPermissions
                );
            }

            case ONLY_FORCED_ASSIGNED -> {
                List<OperationPermissionsEntity> forceAssignedUserPermissions = operationPermissionsRepository.findForceAssignedUserPermissionsByUserId(userId);
                if (forceAssignedUserPermissions == null || forceAssignedUserPermissions.isEmpty())
                    yield new GetUserPermissionResultMessage(
                            4,
                            "У пользователя '" + userId + "' нет назначенных разрешений, соответствующих переданному критерию фильтрации",
                            null
                    );

                resultPermissions.put("forcedAssigned", forceAssignedUserPermissions);
                yield new GetUserPermissionResultMessage(
                        0,
                        "Разрешения пользователя найдены успешно",
                        resultPermissions
                );
            }
        };
    }

    @Override
    public UserAddPermissionResultMessage userAddPermission(UUID userMadeRequestId, UUID userId, List<UUID> permissionIds) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new UserAddPermissionResultMessage(
                 1,
                 "Пользователь, инициировавщий выполнение операции, не найден",
                 null
            );

        if (!universalUserRepository.existsById(userId))
            return new UserAddPermissionResultMessage(
                    2,
                    "Пользователь, для которого выполняется операция назначения разрешений, не найден",
                    null
            );

        if (permissionIds == null || permissionIds.isEmpty())
            return new UserAddPermissionResultMessage(
                    3,
                    "Переданный список идентификаторов назначаемых пользователю разрешений не может быть пустым",
                    null
            );

        UniversalUserEntity addedPermissionsUser = universalUserRepository.getReferenceById(userId);
        UserAddPermissionResultMessage result = new UserAddPermissionResultMessage();
        List<UserAddPermissionResultMessageListElement> resultsPerPermissions = new ArrayList<>();

        for (UUID permissionId : permissionIds) {
            List<UserPermissionEntity> checkReferenceExistenceList = userPermissionRepository
                    .findByUserIdAndPermissionId(userId, permissionId);

            if (!operationPermissionsRepository.existsById(permissionId))
                resultsPerPermissions.add(new UserAddPermissionResultMessageListElement(
                        permissionId,
                        1,
                        "Указанное разрешение не существует"
                ));
            else if (checkReferenceExistenceList != null && !checkReferenceExistenceList.isEmpty())
                resultsPerPermissions.add(new UserAddPermissionResultMessageListElement(
                        permissionId,
                        2,
                        "Указанное разрешение уже назначено пользователю"
                ));
            else {
                OperationPermissionsEntity operationPermissionsEntity = operationPermissionsRepository.getReferenceById(permissionId);
                UserPermissionEntity userPermissionRelationship = new UserPermissionEntity();
                userPermissionRelationship.setPermissionId(operationPermissionsEntity);
                userPermissionRelationship.setUserId(addedPermissionsUser);

                //Getting current date-time
                String currentDateTime = DateTimeFormatter.getCurrentDateTimeStringWithTZ();
                userPermissionRelationship.setCreateDate(currentDateTime);

                UUID savedRelationshipReferenceId = (userPermissionRepository.save(userPermissionRelationship)).getId();

                resultsPerPermissions.add(new UserAddPermissionResultMessageListElement(
                        permissionId,
                        0,
                        "Разрешение успешно назначено"
                ));

                log.info("There was a userPermissionRelationship reference created in table user_permission with parameters: " +
                        "{id:'" + savedRelationshipReferenceId + "',user_id:'" +
                        userId + "',permission_id:'" + permissionId + "',}");
            }
        }

        int unsuccessfullyUserPermissionAddAmount = 0;
        for (UserAddPermissionResultMessageListElement element : resultsPerPermissions) {
            if (element.getCode() != 0)
                unsuccessfullyUserPermissionAddAmount++;
        }

        if (unsuccessfullyUserPermissionAddAmount == resultsPerPermissions.size()) {
            result.setGlobalOperationCode(5);
            result.setMessage("Ни одно из указанных разрешений не было назначено пользователю");
        }
        else if (unsuccessfullyUserPermissionAddAmount != 0) {
            result.setGlobalOperationCode(4);
            result.setMessage("Успешно назначена пользователю лишь часть из списка переданных разрешений");
        } else {
            result.setMessage("Все разрешения были успешно назначены указанному пользователю");
        }

        result.setMessagesPerPermissions(resultsPerPermissions);
        return result;
    }

    @Override
    public UnassignPermissionsResultMessage unassignPermissions(UUID userMadeRequestId, UUID userId, List<UUID> permissionIds) {
        if (userMadeRequestId == null || !universalUserRepository.existsById(userMadeRequestId))
            return new UnassignPermissionsResultMessage(
                    1,
                    "Пользователь, инициировавший операцию, не найден",
                    null
            );

        if (permissionIds == null || permissionIds.isEmpty())
            return new UnassignPermissionsResultMessage(
                    3,
                    "Список идентификаторов permissionIds должен быть заполнен хотя бы одним значением",
                    null
            );

        Optional<UniversalUserEntity> universalUserEntityOptional = universalUserRepository.findById(userId);
        if (universalUserEntityOptional.isEmpty())
            return new UnassignPermissionsResultMessage(
                    2,
                    "Пользователь с указанным userId не существует",
                    null
            );

        List<UnassignPermissionsResultMessageListElement> resultMessageList = new ArrayList<>();
        for (UUID permissionId : permissionIds) {
            Optional<OperationPermissionsEntity> operationPermissionsEntityOptional = operationPermissionsRepository.findById(permissionId);
            List<UserPermissionEntity> userPermissionEntities = userPermissionRepository.findByUserIdAndPermissionId(userId, permissionId);

            if (operationPermissionsEntityOptional.isEmpty())
                resultMessageList.add(new UnassignPermissionsResultMessageListElement(
                        permissionId,
                        1,
                        "Разрешение с указанным идентификатором не существует"
                ));
            else if (userPermissionEntities == null || userPermissionEntities.isEmpty())
                resultMessageList.add(new UnassignPermissionsResultMessageListElement(
                        permissionId,
                        2,
                        "Разрешение с указанным идентификатором не привязано к данному пользователю"
                ));
            else {
                userPermissionRepository.delete(userPermissionEntities.get(0));
                log.info("A reference '" + userPermissionEntities.get(0).getId() + "' was deleted in table roles_operations");
                resultMessageList.add(new UnassignPermissionsResultMessageListElement(
                        permissionId,
                        0,
                        "Разрешение с указанным идентификатором успешно отозвано у данного пользователя"
                ));
            }
        }

        UnassignPermissionsResultMessage result = new UnassignPermissionsResultMessage();

        int unsuccessfullyUnassignPermissionAmount = 0;
        for (UnassignPermissionsResultMessageListElement unassignPermissionsResultMessageListElement : resultMessageList) {
            if (unassignPermissionsResultMessageListElement.getCode() != 0)
                unsuccessfullyUnassignPermissionAmount++;
        }

        if (unsuccessfullyUnassignPermissionAmount == resultMessageList.size()) {
            result.setGlobalOperationCode(5);
            result.setMessage("У указанного пользователя не было отозвано ни одно из разрешений по причине возникновения ошибок");
        } else if (unsuccessfullyUnassignPermissionAmount != 0) {
            result.setGlobalOperationCode(4);
            result.setMessage("У указанного пользователя была отозвана лишь часть разрешений по причине возникновения ошибок");
        } else {
            result.setGlobalOperationCode(0);
            result.setMessage("Все разрешения были успешно отозваны от указанного пользователя.");
        }

        result.setMessages(resultMessageList);
        return result;
    }

    @Override
    public List<UniversalUser> readAll() {
        return UserEntityToUserBOConverter.convertList(universalUserRepository.findAll());
    }
}