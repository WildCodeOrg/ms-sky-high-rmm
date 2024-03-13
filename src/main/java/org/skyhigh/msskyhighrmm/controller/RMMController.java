package org.skyhigh.msskyhighrmm.controller;

import lombok.Getter;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.DTO.rolesControllerDTOs.addUserGroupRoleDTOs.DeliveryRequestAddUserGroupRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.rolesControllerDTOs.addUserGroupRoleDTOs.DeliveryResponseAddUserGroupRoleDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.exceptionDTOs.CommonExceptionResponseDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.getUserByIdDTOs.DeliveryRequestGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.getUserByIdDTOs.DeliveryResponseGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.loginUserDTOs.DeliveryRequestLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.loginUserDTOs.DeliveryResponseLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.registerUserDTOs.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.registerUserDTOs.DeliveryResponseRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.searchUsersDTOs.DeliveryRequestSearchUsersDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.searchUsersDTOs.DeliveryResponseSearchUsersDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.updateUserByIdDTOs.DeliveryRequestUpdateUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserControllerDTOs.updateUserByIdDTOs.DeliveryResponseUpdateUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.SystemObjects.PageInfo;
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
public class RMMController {
    private static final Logger log = Logger.getLogger(RMMController.class.getName());

    private final UniversalUserService universalUserService;
    private final RolesService rolesService;

    @Autowired
    public RMMController(UniversalUserService universalUserService, RolesService rolesService) {
        this.universalUserService = universalUserService;
        this.rolesService = rolesService;
    }

    //project logic - comment it if you wanna just only test the project availability and try the part below

    //Users request mapping

    @ValidParams
    @PostMapping(value = "/users")
    public ResponseEntity<?> registerUser(@RequestBody DeliveryRequestRegisterUserDTO registerUserDTO) {
        log.info("Registering process for '" + registerUserDTO.getLogin() + "' was started");

        final UUID registered_user_id = universalUserService.registerUser(registerUserDTO.getLogin(),
                registerUserDTO.getPassword());

        return registered_user_id != null
                ? new ResponseEntity<>(new DeliveryResponseRegisterUserDTO(registered_user_id),
                    HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    2,
                    "Ошибка регистрации.",
                    400,
                    "Пользователь с таким логином уже существует."
                    ), HttpStatus.BAD_REQUEST);
    }

    @ValidParams
    @PostMapping(value = "/users/login")
    public ResponseEntity<?> loginUser(@RequestBody DeliveryRequestLoginUserDTO loginUserDTO) {
        log.info("Login process for '" + loginUserDTO.getLogin() + "' was started");

        final String login = loginUserDTO.getLogin();
        final UUID id = universalUserService.checkUser(login);

        if (id == null) {
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    3,
                    "Ошибка авторизации.",
                    400,
                    "Данного пользователя не существует."
            ), HttpStatus.BAD_REQUEST);
        }

        return loginUserDTO.getPassword().equals(universalUserService.getUserById(id).getPassword())
                ? new ResponseEntity<>(new DeliveryResponseLoginUserDTO(login, id,
                    "Авторизация пользователя прошла успешно."), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    4,
                    "Ошибка авторизации.",
                    400,
                    "Неправильный пароль."), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "user_id") UUID searchForUserId, @ValidParams
                                         @RequestBody DeliveryRequestGetUserByIdDTO getUserByIdDTO) {
        log.info("Getting user by Id '" + searchForUserId +
                "' process was started by '" + getUserByIdDTO.getUserMadeRequestId() + "'");

        final UUID userMadeRequestId = getUserByIdDTO.getUserMadeRequestId();
        final UniversalUser foundUniversalUser;

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        foundUniversalUser = universalUserService.getUserById(searchForUserId);

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
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        ListOfUniversalUser result = universalUserService.searchUsers(searchUsersDTO.getPagination(),
                searchUsersDTO.getFilters(), searchUsersDTO.getSort());

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
        final UUID userMadeRequestId = updateUserByIdDTO.getUserMadeRequestId();

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        return universalUserService.getUserById(updateUserId) != null
                ? new ResponseEntity<>(new DeliveryResponseUpdateUserByIdDTO(
                    "Запись пользователя успешно обновлена",universalUserService.updateUserById(updateUserId,
                                updateUserByIdDTO.getNewUserInfoAttributes())
                    ), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    6,
                    "Ошибка выполнения операции.",
                    404,
                    "Искомый пользователь не найден."
                    ), HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа.",
                    401,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.UNAUTHORIZED);
        }

        final UUID addedUserGroupRoleId = rolesService.addRole(addUserGroupRoleDTO.getRole_name(),
                addUserGroupRoleDTO.getDescription(), false);

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

    @

    //test controller methods - uncomment to test the project availability

    /*
    @ValidParams
    @GetMapping("/check")
    public void checkDeliveryAvailability(@RequestBody DeliveryRequestRegisterUserDTO requestDto) {
        System.out.println("Validation!");
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody UniversalUser universalUser) {
        universalUserService.create(universalUser);
        //log.info(universalUser.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UniversalUser>> read() {
        final List<UniversalUser> users = universalUserService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<UniversalUser> read(@PathVariable(name = "user_id") UUID user_id) {
        final UniversalUser client = universalUserService.read(user_id);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{user_id}")
    public ResponseEntity<?> update(@PathVariable(name = "user_id") UUID user_id, @RequestBody UniversalUser universalUser) {
        final boolean updated = universalUserService.update(universalUser, user_id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/{user_id}")
    public ResponseEntity<?> delete(@PathVariable(name = "user_id") UUID user_id) {
        final boolean deleted = universalUserService.delete(user_id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/
}
