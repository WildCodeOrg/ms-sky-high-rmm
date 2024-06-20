package org.skyhigh.msskyhighrmm.controller;

import lombok.Getter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Permissions.ListOfOperationPermissions;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.CreatePermission.DeliveryRequestCreatePermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.CreatePermission.DeliveryResponseCreatePermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.SearchPermissions.DeliveryRequestSearchPermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.SearchPermissions.DeliveryResponseSearchPermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.UpdatePermission.DeliveryRequestUpdatePermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.permissionsRMMController.UpdatePermission.DeliveryResponseUpdatePermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addPermissionsDTOs.AddPermissionExceptionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addPermissionsDTOs.DeliveryRequestAddPermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addPermissionsDTOs.DeliveryResponseAddPermissionDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addUserGroupRoleDTOs.DeliveryRequestAddUserGroupRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.addUserGroupRoleDTOs.DeliveryResponseAddUserGroupRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.deleteUserGroupRoleDTOs.DeliveryRequestDeleteUserGroupRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.deleteUserGroupRoleDTOs.DeliveryResponseDeleteUserGroupRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.searchRolesDTOs.DeliveryRequestSearchRolesDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.searchRolesDTOs.DeliveryResponseSearchRolesDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.updateRoleDTOs.DeliveryRequestUpdateRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesRMMControllerDTOs.updateRoleDTOs.DeliveryResponseUpdateRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addAdminKeyCodeDTOs.DeliveryRequestAddAdminKeyCodeDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addAdminKeyCodeDTOs.DeliveryResponseAddAdminKeyCodeDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addBlockReasonDTOs.DeliveryRequestAddBlockReasonDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addBlockReasonDTOs.DeliveryResponseAddBlockReasonDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addRoleToUserDTOs.DeliveryRequestAddRoleToUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addRoleToUserDTOs.DeliveryResponseAddRoleToUserFullSuccessfulDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.addRoleToUserDTOs.DeliveryResponseAddRoleToUserPartlySuccessfulDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.blockUserDTOs.DeliveryRequestBlockUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.blockUserDTOs.DeliveryResponseBlockUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.exceptionDTOs.CommonExceptionResponseDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserByIdDTOs.DeliveryRequestGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserByIdDTOs.DeliveryResponseGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserRolesDTOs.DeliveryRequestGetUserRolesDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserRolesDTOs.DeliveryResponseGetUserRolesDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.loginUserDTOs.DeliveryRequestLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.loginUserDTOs.DeliveryResponseLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.registerUserDTOs.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.registerUserDTOs.DeliveryResponseRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.removeRoleFromUserListDTOs.DeliveryRequestRemoveRoleFromUserListDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.removeRoleFromUserListDTOs.DeliveryResponseRemoveRoleFromUserListFullSuccessDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.removeRoleFromUserListDTOs.DeliveryResponseRemoveRoleFromUserListPartlySuccessDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.searchUsersDTOs.DeliveryRequestSearchUsersDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.searchUsersDTOs.DeliveryResponseSearchUsersDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.updateUserByIdDTOs.DeliveryRequestUpdateUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.updateUserByIdDTOs.DeliveryResponseUpdateUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.CreatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.UpdatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.AddPermissions.AddPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UpdateRole.UpdateRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddAdminKey.AddAdminKeyResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddBlockReason.AddBlockReasonResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddRoleToUser.AddRoleToUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles.GetUserRolesResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser.LoginUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RegisterUser.RegisterUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList.RemoveRoleFromUserListResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PageInfo;
import org.skyhigh.msskyhighrmm.service.PermissionService.PermissionService;
import org.skyhigh.msskyhighrmm.service.RolesService.RolesService;
import org.skyhigh.msskyhighrmm.service.UniversalUserService.UniversalUserService;
import org.skyhigh.msskyhighrmm.validation.SpringAspect.annotationsApi.ValidParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Getter
@RestController
@RequestMapping("/rmm-service/api")
public class RMMController {
    private static final Logger log = Logger.getLogger(RMMController.class.getName());

    private final UniversalUserService universalUserService;
    private final RolesService rolesService;
    private final PermissionService permissionService;

    @Autowired
    public RMMController(UniversalUserService universalUserService, RolesService rolesService, PermissionService permissionService) {
        this.universalUserService = universalUserService;
        this.rolesService = rolesService;
        this.permissionService = permissionService;
    }

    //Users request mapping

    @ValidParams
    @PostMapping(value = "/users")
    public ResponseEntity<?> registerUser(@RequestBody DeliveryRequestRegisterUserDTO registerUserDTO) {
        log.info("Registering process for '" + registerUserDTO.getLogin() + "' was started");

        final RegisterUserResultMessage result = universalUserService.registerUser(registerUserDTO.getLogin(),
                registerUserDTO.getPassword(), registerUserDTO.isAdminRegistration(), registerUserDTO.getAdminKey());

        return switch (result.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished successfully (id: '" + result.getCreatedUserId() + "')");
                yield new ResponseEntity<>(new DeliveryResponseRegisterUserDTO(
                        result.getCreatedUserId()
                ), HttpStatus.OK);
            }

            case 1 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with login validation exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10001,
                        "Ошибка регистрации.",
                        400,
                        "Длина логина должна быть от 6 до 20 символов."
                ), HttpStatus.BAD_REQUEST);
            }

            case 2 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with password validation exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10002,
                        "Ошибка регистрации.",
                        400,
                        "Длина пароля должна быть от 8 до 20 символов."
                ), HttpStatus.BAD_REQUEST);
            }

            case 3 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with UserAlreadyExists exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10003,
                        "Ошибка регистрации.",
                        400,
                        "Пользователь с таким логином уже существует."
                ), HttpStatus.BAD_REQUEST);
            }

            case 4 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with AdminKeyIsEmpty exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10004,
                        "Ошибка регистрации.",
                        400,
                        "При регистрации администратора заполнение ключа администатора является обязательным."
                ), HttpStatus.BAD_REQUEST);
            }

            case 5 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with AdminKeySize exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10005,
                        "Ошибка регистрации.",
                        400,
                        "Длина ключа администратора 32 символа."
                ), HttpStatus.BAD_REQUEST);
            }

            case 6 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with AdminKeyNotExist exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10006,
                        "Ошибка регистрации.",
                        400,
                        "Указанного ключа администратора не существует."
                ), HttpStatus.BAD_REQUEST);
            }

            case 7 -> {
                log.warning("Registering a user with login '" + registerUserDTO.getLogin() +
                        "' process finished with AdminWithCertainKeyAlreadyExists exception");

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10007,
                        "Ошибка регистрации.",
                        400,
                        "Указанный ключ администратора уже используется другим пользователем."
                ), HttpStatus.BAD_REQUEST);
            }

            default -> throw new IllegalStateException("Unexpected value: " + result.getGlobalOperationCode());
        };
    }

    @ValidParams
    @PostMapping(value = "/users/login")
    public ResponseEntity<?> loginUser(@RequestBody DeliveryRequestLoginUserDTO loginUserDTO) {
        log.info("Login process for '" + loginUserDTO.getLogin() + "' was started");

        LoginUserResultMessage loginResult = universalUserService.loginUser(
                loginUserDTO.getLogin(),
                loginUserDTO.getPassword()
        );

        log.info("While logging by login '" + loginUserDTO.getLogin() + "' process got loginResult: {" +
                loginResult.toString() + "}");

        return switch (loginResult.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Login process for '" + loginUserDTO.getLogin() + "' was finished successfully");

                yield new ResponseEntity<>(new DeliveryResponseLoginUserDTO(
                    loginUserDTO.getLogin(),
                    loginResult.getLogonUserId(),
                    "Пользователь успешно авторизован."
                ), HttpStatus.OK);
            }
            case 1 -> {
                log.warning("Login process for '" + loginUserDTO.getLogin() + "' was finished with error: "
                        + loginResult.getGlobalMessage());

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        11001,
                        "Ошибка авторизации.",
                        400,
                        loginResult.getGlobalMessage()
                ), HttpStatus.BAD_REQUEST);
            }
            case 2 -> {
                log.warning("Login process for '" + loginUserDTO.getLogin() + "' was finished with error: "
                    + loginResult.getGlobalMessage());

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        11002,
                        "Ошибка авторизации.",
                        400,
                        loginResult.getGlobalMessage()
                ), HttpStatus.BAD_REQUEST);
            }
            default -> throw new IllegalStateException("Unexpected value: " + loginResult.getGlobalOperationCode());
        };
    }

    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "user_id") UUID searchForUserId, @ValidParams
                                         @RequestBody DeliveryRequestGetUserByIdDTO getUserByIdDTO) {
        log.info("Getting user by Id '" + searchForUserId +
                "' process was started by '" + getUserByIdDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = getUserByIdDTO.getUserMadeRequestId();
        final UniversalUser foundUniversalUser;

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Getting user by Id '" + searchForUserId +
                    "' process started by '" + getUserByIdDTO.getUserMadeRequestId() +
                    "' finished with exception code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        foundUniversalUser = universalUserService.getUserById(searchForUserId);

        log.info("Getting user by Id '" + searchForUserId +
                "' process started by '" + getUserByIdDTO.getUserMadeRequestId() + "'" +
                " was finished");
        return foundUniversalUser != null
                ? new ResponseEntity<>(new DeliveryResponseGetUserByIdDTO("Пользователь найден.",
                    foundUniversalUser), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    6,
                    "Ошибка выполнения операции.",
                    404,
                    "Искомый пользователь не найден."
                    ), HttpStatus.NOT_FOUND);
    }

    @ValidParams
    @PostMapping(value = "/users/search")
    public ResponseEntity<?> searchUsers(@RequestBody DeliveryRequestSearchUsersDTO searchUsersDTO) {
        log.info("Getting users by criteria process was started by '" + searchUsersDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = searchUsersDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Getting users by criteria process started by '" + searchUsersDTO.getUserMadeRequestId() +
                    "' finished with exception code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        ListOfUniversalUser result = universalUserService.searchUsers(searchUsersDTO.getPagination(),
                searchUsersDTO.getFilters(), searchUsersDTO.getSort());

        log.info("Getting users by criteria process started by '" + searchUsersDTO.getUserMadeRequestId() +
                "' finished");

        return result != null
                ? new ResponseEntity<>(new DeliveryResponseSearchUsersDTO(result.getItemCount(),
                    new PageInfo(result.getPageNumber(), result.getPaginationItemCount()),
                    result.getUniversalUsers()), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    7,
                    "Ошибка выполнения поиска пользователей по параметрам.",
                    404,
                    "Пользователи, удовлетворяющие критериям поиска, не найдены."
                    ), HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{user_id}")
    public ResponseEntity<?> updateUserById(@PathVariable(name = "user_id") UUID updateUserId, @ValidParams
                                            @RequestBody DeliveryRequestUpdateUserByIdDTO updateUserByIdDTO) {
        log.info("Updating user with id '" + updateUserId +
                "' process was started by '" + updateUserByIdDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = updateUserByIdDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Updating user with id '" + updateUserId +
                    "' process started by '" + updateUserByIdDTO.getUserMadeRequestId() +
                    "' finished with exception code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        log.info("Updating user with id '" + updateUserId +
                "' process started by '" + updateUserByIdDTO.getUserMadeRequestId() +
                "' finished");
        return universalUserService.getUserById(updateUserId) != null
                ? new ResponseEntity<>(new DeliveryResponseUpdateUserByIdDTO(
                    "Запись пользователя успешно обновлена", universalUserService.updateUserById(updateUserId,
                                updateUserByIdDTO.getNewUserInfoAttributes())
                    ), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    6,
                    "Ошибка выполнения операции.",
                    404,
                    "Искомый пользователь не найден."
                    ), HttpStatus.NOT_FOUND);
    }

    @ValidParams
    @PostMapping
    public ResponseEntity<?> blockUsers(@RequestBody DeliveryRequestBlockUserDTO blockUserDTO) {
        log.info("Blocking users with request '" + blockUserDTO.toString() +
                "' process was started by '" + blockUserDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = blockUserDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Blocking users with request '" + blockUserDTO.toString() +
                    "' process started by '" + blockUserDTO.getUserMadeRequestId() +
                    "' finished with error code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        BlockUsersResultMessage resultMessage = universalUserService.blockUsers(
                blockUserDTO.getUsersToBlock(),
                blockUserDTO.getUserToBlockId(),
                blockUserDTO.getBlockReasonId()
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Blocking users with request '" + blockUserDTO +
                        "' process was finished successfully");
                yield new ResponseEntity<>(new DeliveryResponseBlockUserDTO(
                        "Операция выполнена успешно: " + resultMessage.getGlobalMessage(),
                        resultMessage.getCertainBlockUsersResults()
                ), HttpStatus.OK);
            }
            case 1 -> {
                log.warning("Blocking users with request '{" + blockUserDTO +
                        "}' process was finished half successfully (with some errors)");
                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        12,
                        "Операция выполнена с ошибками (частично успешно): " + resultMessage.getGlobalMessage(),
                        400,
                        resultMessage.getCertainBlockUsersResults().toString()
                ), HttpStatus.BAD_REQUEST);
            }
            case 2 -> {
                log.warning("Blocking users with request '" + blockUserDTO +
                        "' process was finished unsuccessfully (all data pairs with errors)");
                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        13,
                        "Ошибка выполнения операции: " + resultMessage.getGlobalMessage(),
                        400,
                        resultMessage.getCertainBlockUsersResults().toString()
                ), HttpStatus.BAD_REQUEST);
            }
            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UniversalUser>> read() {
        final List<UniversalUser> users = universalUserService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //Roles request mapping

    @ValidParams
    @PostMapping(value = "/roles")
    public ResponseEntity<?> addUserGroupRole(@RequestBody DeliveryRequestAddUserGroupRoleDTO addUserGroupRoleDTO) {
        log.info("Adding process for role '" + addUserGroupRoleDTO.getRole_name() + "' was started");

        final UUID userMadeRequestId = addUserGroupRoleDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.info("Adding process for role '" + addUserGroupRoleDTO.getRole_name() +
                    "' was finished with error code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        final UUID addedUserGroupRoleId = rolesService.addRole(addUserGroupRoleDTO.getRole_name(),
                addUserGroupRoleDTO.getDescription());

        log.info("Adding process for role '" + addUserGroupRoleDTO.getRole_name() + "' was finished");
        return addedUserGroupRoleId != null
                ? new ResponseEntity<>(new DeliveryResponseAddUserGroupRoleDTO("Роль успешно добавлена.",
                        addedUserGroupRoleId), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    8,
                    "Ошибка создания роли.",
                    400,
                    "Роль с таким наименованием уже существует."
        ), HttpStatus.BAD_REQUEST);
    }

    @ValidParams
    @PostMapping(value = "/roles/search")
    public ResponseEntity<?> searchRoles(@RequestBody DeliveryRequestSearchRolesDTO searchRolesDTO) {
        log.info("Getting roles by criteria process was started by '" + searchRolesDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = searchRolesDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Getting roles by criteria process started by '" + searchRolesDTO.getUserMadeRequestId() +
                    "' finished with error code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        ListOfUserGroupRoles result = rolesService.rolesSearch(searchRolesDTO.getPagination(),
                searchRolesDTO.getRoleId(), searchRolesDTO.getFilters(), searchRolesDTO.getSort());

        log.info("Getting roles by criteria process started by '" + searchRolesDTO.getUserMadeRequestId() +
                "' was finished");
        return result != null
                ? new ResponseEntity<>(new DeliveryResponseSearchRolesDTO(result.getItemCount(),
                new PageInfo(result.getPageNumber(), result.getPaginationItemCount()),
                result.getRoles()), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                9,
                "Ошибка выполнения поиска ролей по параметрам.",
                404,
                "Роли, удовлетворяющие критериям поиска, не найдены."
        ), HttpStatus.NOT_FOUND);
    }

    @ValidParams
    @DeleteMapping(value = "/roles")
    public ResponseEntity<?> deleteRole(@RequestBody DeliveryRequestDeleteUserGroupRoleDTO deleteUserGroupRoleDTO) {
        log.info("Deleting a role with id '" + deleteUserGroupRoleDTO.getRoleToDeleteId() +
                "' process was started by '" + deleteUserGroupRoleDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = deleteUserGroupRoleDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Deleting a role with id '" + deleteUserGroupRoleDTO.getRoleToDeleteId() +
                    "' process finished with error status" + 5);

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        DeleteUserGroupRoleResultMessage result = rolesService.
                deleteRole(deleteUserGroupRoleDTO.getRoleToDeleteId());

        return switch (result.getOperationCode()) {
            case 0 -> {
                log.info("Deleting a role with id '" + deleteUserGroupRoleDTO.getRoleToDeleteId() +
                        "' process finished successfully");
                yield new ResponseEntity<>(new DeliveryResponseDeleteUserGroupRoleDTO(
                    result.getMessage()
                ), HttpStatus.OK);
            }
            case 1 -> {
                log.warning("Deleting a role with id '" + deleteUserGroupRoleDTO.getRoleToDeleteId() +
                        "' process finished with error status" + 10);
                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        10,
                        "Ошибка выполнения удаления роли",
                        404,
                        result.getMessage()
                ), HttpStatus.NOT_FOUND);
            }
            case 2 -> {
                log.warning("Deleting a role with id '" + deleteUserGroupRoleDTO.getRoleToDeleteId() +
                        "' process finished with error status" + 11);
                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        11,
                        "Ошибка выполнения удаления роли",
                        400,
                        result.getMessage()
                ), HttpStatus.BAD_REQUEST);
            }
            default -> throw new IllegalStateException("Unexpected value: " + result.getOperationCode());
        };
    }

    @ValidParams
    @PostMapping(value = "/admins/key-code-value")
    public ResponseEntity<?> addAdminKeyCode(@RequestBody DeliveryRequestAddAdminKeyCodeDTO deliveryRequestAddAdminKeyCodeDTO) {
        log.info("Adding an admin code: '" + deliveryRequestAddAdminKeyCodeDTO.getAdminKeyCode() +
                "' process was started by '" + deliveryRequestAddAdminKeyCodeDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = deliveryRequestAddAdminKeyCodeDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Adding an admin code: '" + deliveryRequestAddAdminKeyCodeDTO.getAdminKeyCode() +
                    "' process was finished with error as user made request does not exist");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        AddAdminKeyResultMessage resultMessage = universalUserService.addAdminKey(
                userMadeRequestId,
                deliveryRequestAddAdminKeyCodeDTO.getAdminKeyCode()
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Adding an admin code: '" + deliveryRequestAddAdminKeyCodeDTO.getAdminKeyCode() +
                        "' process was finished successfully:" + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new DeliveryResponseAddAdminKeyCodeDTO(
                        "Ключ-код администратора успешно добавлен.",
                        resultMessage.getAdminKeyReferenceId()
                ), HttpStatus.OK);
            }
            case 1 -> {
                log.warning("Adding an admin code: '" + deliveryRequestAddAdminKeyCodeDTO.getAdminKeyCode() +
                        "' process was finished with error: " + resultMessage.getGlobalMessage());
                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        13001,
                        "Ошибка выполнения загрузки ключ-кода администратора.",
                        400,
                        "Длина ключ-кода администратора 32 символа."
                ), HttpStatus.BAD_REQUEST);
            }
            case 2 -> {
                log.warning("Adding an admin code: '" + deliveryRequestAddAdminKeyCodeDTO.getAdminKeyCode() +
                        "' process was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        13002,
                        "Ошибка выполнения загрузки ключ-кода администратора.",
                        400,
                        "Пользователь, инициировавший операцию, не обладает требуемыми правами."
                ), HttpStatus.BAD_REQUEST);
            }
            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }

    @ValidParams
    @PostMapping(value = "/users/roles")
    public ResponseEntity<?> addRoleToUser(@RequestBody DeliveryRequestAddRoleToUserDTO deliveryRequestAddRoleToUserDTO) {
        log.info("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                "' process was started by '" + deliveryRequestAddRoleToUserDTO.getUserMadeRequestId() + "'");

        AddRoleToUserResultMessage resultMessage = universalUserService.addRoleToUsers(
                deliveryRequestAddRoleToUserDTO.getUserMadeRequestId(),
                deliveryRequestAddRoleToUserDTO.getRoleId(),
                deliveryRequestAddRoleToUserDTO.getUsersToAddRoleIds()
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                    "' process was finished successfully: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new DeliveryResponseAddRoleToUserFullSuccessfulDTO(
                        resultMessage.getGlobalMessage()
                ), HttpStatus.OK);
            }
            case 1 -> {
                log.warning("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                        "' process was finished partly successfully: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new DeliveryResponseAddRoleToUserPartlySuccessfulDTO(
                        resultMessage.getGlobalMessage(),
                        resultMessage.getCertainAddRoleToUsersResults()
                ), HttpStatus.OK);
            }
            case 2 -> {
                log.warning("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                        "' process was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new DeliveryResponseAddRoleToUserPartlySuccessfulDTO(
                        resultMessage.getGlobalMessage(),
                        resultMessage.getCertainAddRoleToUsersResults()
                ), HttpStatus.BAD_REQUEST);
            }
            case 3 -> {
                log.warning("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                        "' process was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        5,
                        "Ошибка прав доступа.",
                        401,
                        resultMessage.getGlobalMessage()
                ), HttpStatus.UNAUTHORIZED);
            }
            case 4 -> {
                log.warning("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                        "' process was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        14003,
                        "Ошибка выполнения добавления роли списку пользователей.",
                        404,
                        resultMessage.getGlobalMessage()
                ), HttpStatus.NOT_FOUND);
            }
            case 5 -> {
                log.warning("Adding a role: '" + deliveryRequestAddRoleToUserDTO.getRoleId() +
                        "' process was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(new CommonExceptionResponseDTO(
                        14004,
                        "Ошибка выполнения добавления роли списку пользователей.",
                        400,
                        resultMessage.getGlobalMessage()
                ), HttpStatus.BAD_REQUEST);
            }
            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }


    @GetMapping(value = "/users/{user_id}/roles")
    public ResponseEntity<?> getUserRoles(@PathVariable(name = "user_id") UUID userId, @ValidParams
                                          @RequestBody DeliveryRequestGetUserRolesDTO getUserRolesDTO) {
        log.info("Getting roles for user: '" + userId + "' " +
                "process was started by '" + getUserRolesDTO.getUserMadeRequestId() + "'");

        GetUserRolesResultMessage resultMessage = universalUserService.getUserRoles(
                getUserRolesDTO.getUserMadeRequestId(),
                userId
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Getting roles for user: '" + userId + "' " +
                        "process started by '" + getUserRolesDTO.getUserMadeRequestId() +
                        "' was finished successfully: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new DeliveryResponseGetUserRolesDTO(
                                resultMessage.getGlobalMessage(),
                                resultMessage.getRolesOfUser()
                        )
                , HttpStatus.OK);
            }
            case 1 -> {
                log.warning("Getting roles for user: '" + userId + "' " +
                        "process started by '" + getUserRolesDTO.getUserMadeRequestId() +
                        "' was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                             5,
                             "Ошибка прав доступа",
                             401,
                                resultMessage.getGlobalMessage()
                        )
                , HttpStatus.UNAUTHORIZED);
            }
            case 2 -> {
                log.warning("Getting roles for user: '" + userId + "' " +
                        "process started by '" + getUserRolesDTO.getUserMadeRequestId() +
                        "' was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                15001,
                                "Ошибка выполнения поиска ролей пользователя",
                                404,
                                resultMessage.getGlobalMessage()
                        )
                , HttpStatus.NOT_FOUND);
            }
            case 3 -> {
                log.warning("Getting roles for user: '" + userId + "' " +
                        "process started by '" + getUserRolesDTO.getUserMadeRequestId() +
                        "' was finished with error: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                15002,
                                "Ошибка выполнения поиска ролей пользователя",
                                404,
                                resultMessage.getGlobalMessage()
                        )
                , HttpStatus.NOT_FOUND);
            }
            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }


    @DeleteMapping(value = "/users/roles/{role_id}")
    public ResponseEntity<?> removeRoleFromUserList(@PathVariable(value = "role_id") UUID roleId, @ValidParams
                                                    @RequestBody DeliveryRequestRemoveRoleFromUserListDTO removeRoleFromUserListRequest) {

        log.info("Removing role with id: '" + roleId + "' " +
                " for user list process was started by " +
                "'" + removeRoleFromUserListRequest.getUserMadeRequestId() + "'");

        RemoveRoleFromUserListResultMessage resultMessage = universalUserService.removeRoleFromUserList(
                removeRoleFromUserListRequest.getUserMadeRequestId(),
                roleId,
                removeRoleFromUserListRequest.getUserIds()
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished successfully");

                yield new ResponseEntity<>(
                        new DeliveryResponseRemoveRoleFromUserListFullSuccessDTO(
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.OK
                );
            }

            case 1 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished partly successfully");

                yield new ResponseEntity<>(
                        new DeliveryResponseRemoveRoleFromUserListPartlySuccessDTO(
                                resultMessage.getGlobalMessage(),
                                resultMessage.getCertainResultMessages()
                        ),
                        HttpStatus.OK
                );
            }

            case 2 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                16001,
                                "Ошибка выполнения отмены роли списку пользователей",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 3 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                5,
                                "Ошибка прав доступа",
                                401,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.UNAUTHORIZED
                );
            }

            case 4 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                16002,
                                "Ошибка выполнения отмены роли списку пользователей",
                                404,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.NOT_FOUND
                );
            }

            case 5 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                16003,
                                "Ошибка выполнения отмены роли списку пользователей",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 6 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                16004,
                                "Ошибка выполнения отмены роли списку пользователей",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 7 -> {
                log.warning("Removing role with id: '" + roleId + "' " +
                        " for user list process was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                16006,
                                "Ошибка выполнения отмены роли списку пользователей",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }

    @ValidParams
    @PostMapping(value = "/blocks")
    public ResponseEntity<?> addBlockReason(@RequestBody DeliveryRequestAddBlockReasonDTO addBlockReasonRequest) {
        log.info("Adding block reason process was started by " +
                "'" + addBlockReasonRequest.getUserMadeRequestId() + "'");

        AddBlockReasonResultMessage resultMessage = universalUserService.addBlockReason(
                addBlockReasonRequest.getUserMadeRequestId(),
                addBlockReasonRequest.getBlockReasonDescription()
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Adding block reason process started by " +
                        "'" + addBlockReasonRequest.getUserMadeRequestId() + "'" +
                        "was finished successfully: created a reference in block_reason table" +
                        "with id '" + resultMessage.getCreatedReferenceId() + "'");

                yield new ResponseEntity<>(
                        new DeliveryResponseAddBlockReasonDTO(
                                "Причина блокировки успешно сохранена.",
                                resultMessage.getCreatedReferenceId()
                        ),
                        HttpStatus.OK
                );
            }

            case 1 -> {
                log.warning("Adding block reason process started by " +
                        "'" + addBlockReasonRequest.getUserMadeRequestId() + "'" +
                        "was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                5,
                                "Ошибка прав доступа",
                                401,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.UNAUTHORIZED
                );
            }

            case 2 -> {
                log.warning("Adding block reason process started by " +
                        "'" + addBlockReasonRequest.getUserMadeRequestId() + "'" +
                        "was finished with exception: " +
                        resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                17001,
                                "Ошибка выполнения добавления причины блокировки в Систему",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }

    @PutMapping(value = "/roles/{role_id}")
    public ResponseEntity<?> updateRole(@PathVariable(value = "role_id") UUID roleId, @ValidParams
                                        @RequestBody DeliveryRequestUpdateRoleDTO updateRoleRequest) {

        log.info("Updating role with id '" + roleId + "' process was started by " +
                "'" + updateRoleRequest.getUserMadeRequestId() + "'");

        UpdateRoleResultMessage resultMessage = rolesService.updateRole(
                updateRoleRequest.getUserMadeRequestId(),
                roleId,
                updateRoleRequest.getRoleName(),
                updateRoleRequest.getDescription()
        );

        return switch (resultMessage.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished successfully");

                yield new ResponseEntity<>(
                        new DeliveryResponseUpdateRoleDTO(
                                resultMessage.getGlobalMessage(),
                                roleId
                        ),
                        HttpStatus.OK
                );
            }

            case 1 -> {
                log.warning("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished with exception: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                5,
                                "Ошибка прав доступа",
                                401,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.UNAUTHORIZED
                );
            }

            case 2 -> {
                log.warning("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished with exception: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                18001,
                                "Ошибка выполнения операции обновления роли",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 3 -> {
                log.warning("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished with exception: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                18002,
                                "Ошибка выполнения операции обновления роли",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 4 -> {
                log.warning("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished with exception: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                18003,
                                "Ошибка выполнения операции обновления роли",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 5 -> {
                log.warning("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished with exception: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                18004,
                                "Ошибка выполнения операции обновления роли",
                                400,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 6 -> {
                log.warning("Updating role with id '" + roleId + "' process started by " +
                        "'" + updateRoleRequest.getUserMadeRequestId() + "'" +
                        "finished with exception: " + resultMessage.getGlobalMessage());

                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                18005,
                                "Ошибка выполнения операции обновления роли",
                                404,
                                resultMessage.getGlobalMessage()
                        ),
                        HttpStatus.NOT_FOUND
                );
            }

            default -> throw new IllegalStateException("Unexpected value: " + resultMessage.getGlobalOperationCode());
        };
    }

    @ValidParams
    @PostMapping(value = "/permissions")
    public ResponseEntity<?> createPermission(@RequestBody DeliveryRequestCreatePermissionDTO createPermissionDTO) {
        log.info("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                + "' process was started by " +
                "'" + createPermissionDTO.getUserMadeRequestId() + "'");

        CreatePermissionResultMessage result = permissionService.createPermission(
                createPermissionDTO.getUserMadeRequestId(),
                createPermissionDTO.getPermissionName(),
                createPermissionDTO.getPermissionEndpoint()
        );

        return switch (result.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished successfully. Created permission id: " +
                        "'" + result.getPermissionID() + "'");
                yield new ResponseEntity<>(
                        new DeliveryResponseCreatePermissionDTO(
                                result.getMessage(),
                                result.getPermissionID()
                        ),
                        HttpStatus.OK
                );
            }

            case 1 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                5,
                                "Ошибка прав доступа.",
                                401,
                                result.getMessage()
                        ),
                        HttpStatus.UNAUTHORIZED
                );
            }

            case 2 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19001,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 3 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19002,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 4 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19003,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 5 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19004,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 6 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19005,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 7 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19006,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 8 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19007,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 9 -> {
                log.warning("Creating a permission with name '" + createPermissionDTO.getPermissionName()
                        + "' process started by " +
                        "'" + createPermissionDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                19008,
                                "Ошибка выполнения операции создания нового некритического разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            default -> throw new IllegalStateException("Unexpected value: " + result.getGlobalOperationCode());
        };
    }


    @PutMapping(value = "/permissions/{permission_id}")
    public ResponseEntity<?> updatePermission(@PathVariable(value = "permission_id") UUID permissionId, @ValidParams
                                                   @RequestBody DeliveryRequestUpdatePermissionDTO requestDTO) {
        log.info("Updating a permission with id '" + permissionId
                + "' process was started by " +
                "'" + requestDTO.getUserMadeRequestId() + "'");

        UpdatePermissionResultMessage result = permissionService.updatePermission(
                requestDTO.getUserMadeRequestId(),
                permissionId,
                requestDTO.getPermissionName(),
                requestDTO.getPermissionEndpoint()
        );

        return switch (result.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished successfully");
                yield new ResponseEntity<>(
                        new DeliveryResponseUpdatePermissionDTO(
                                permissionId,
                                result.getMessage()
                        ),
                        HttpStatus.OK
                );
            }

            case 1 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                5,
                                "Ошибка прав доступа.",
                                401,
                                result.getMessage()
                        ),
                        HttpStatus.UNAUTHORIZED
                );
            }

            case 2 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20001,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 3 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20002,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 4 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20003,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 5 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20004,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 6 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20005,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 7 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20006,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 8 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20007,
                                "Ошибка выполнения операции обновления разрешения",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            case 9 -> {
                log.warning("Updating a permission with id '" + permissionId
                        + "' process started by " +
                        "'" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                20008,
                                "Ошибка выполнения операции обновления разрешения",
                                404,
                                result.getMessage()
                        ),
                        HttpStatus.NOT_FOUND
                );
            }

            default -> throw new IllegalStateException("Unexpected value: " + result.getGlobalOperationCode());
        };
    }

    @ValidParams
    @PostMapping(value = "/permissions/search")
    public ResponseEntity<?> searchPermissions(@RequestBody DeliveryRequestSearchPermissionDTO searchPermissionDTO) {
        log.info("Getting permissions by criteria process was started by '" + searchPermissionDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = searchPermissionDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            log.warning("Getting permissions by criteria process started by '" + searchPermissionDTO.getUserMadeRequestId() +
                    "' finished with error code 5");

            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        ListOfOperationPermissions result = permissionService.permissionSearch(searchPermissionDTO.getPagination(),
                searchPermissionDTO.getPermissionId(), searchPermissionDTO.getFilters(), searchPermissionDTO.getSort());

        log.info("Getting permissions by criteria process started by '" + searchPermissionDTO.getUserMadeRequestId() +
                "' was finished");
        return result != null
                ? new ResponseEntity<>(new DeliveryResponseSearchPermissionDTO(result.getItemCount(),
                new PageInfo(result.getPageNumber(), result.getPaginationItemCount()),
                result.getPermissions()), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                21001,
                "Ошибка выполнения поиска разрешений по параметрам.",
                404,
                "Разрешения, удовлетворяющие критериям поиска, не найдены."
        ), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/roles/{role_id}/permissions")
    public ResponseEntity<?> addPermissions(@PathVariable(value = "role_id") UUID roleId, @ValidParams
                                            @RequestBody DeliveryRequestAddPermissionDTO requestDTO) {

        log.info("Adding permissions for role '" + roleId +
                "' process was started by '" + requestDTO.getUserMadeRequestId() + "'");

        AddPermissionsResultMessage result = rolesService.addPermissions(
                requestDTO.getUserMadeRequestId(),
                roleId,
                requestDTO.getPermissionIds()
        );

        return switch (result.getGlobalOperationCode()) {
            case 0 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished successfully");
                yield new ResponseEntity<>(
                        new DeliveryResponseAddPermissionDTO(
                                result.getMessage(),
                                result.getResults()
                        ),
                        HttpStatus.OK
                );
            }
            case 1 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                5,
                                "Ошибка прав доступа.",
                                401,
                                "Пользователь, инициировавший операцию, не найден."
                        ),
                        HttpStatus.UNAUTHORIZED
                );
            }
            case 2 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                22001,
                                "Ошибка выполнения операции добавления разрешений для указанной роли.",
                                404,
                                result.getMessage()
                        ),
                        HttpStatus.NOT_FOUND
                );
            }
            case 3 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                22002,
                                "Ошибка выполнения операции добавления разрешений для указанной роли.",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }
            case 4 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new CommonExceptionResponseDTO(
                                22003,
                                "Ошибка выполнения операции добавления разрешений для указанной роли.",
                                400,
                                result.getMessage()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }
            case 5 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new AddPermissionExceptionDTO(
                                22004,
                                "Ошибка выполнения операции добавления разрешений для указанной роли.",
                                200,
                                result.getMessage(),
                                result.getResults()
                        ),
                        HttpStatus.OK
                );
            }
            case 6 -> {
                log.info("Adding permissions for role '" + roleId +
                        "' process started by '" + requestDTO.getUserMadeRequestId() + "'" +
                        " finished with exception: " + result.getMessage());
                yield new ResponseEntity<>(
                        new AddPermissionExceptionDTO(
                                22005,
                                "Ошибка выполнения операции добавления разрешений для указанной роли.",
                                400,
                                result.getMessage(),
                                result.getResults()
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }
            default -> throw new IllegalStateException("Unexpected value: " + result.getGlobalOperationCode());
        };
    }


    //test controller methods - uncomment to test the project availability

    /*
    @ValidParams
    @GetMapping("/check")
    public void checkDeliveryAvailability(@RequestBody DeliveryRequestRegisterUserDTO requestDto) {
        System.out.println("Validation!");
    }*/
}