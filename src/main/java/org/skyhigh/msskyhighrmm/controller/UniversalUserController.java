package org.skyhigh.msskyhighrmm.controller;

import org.skyhigh.msskyhighrmm.model.DTO.CommonExceptionResponseDTO;
import org.skyhigh.msskyhighrmm.model.DTO.getUserByIdDTOs.DeliveryRequestGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.getUserByIdDTOs.DeliveryResponseGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.loginUserDTOs.DeliveryRequestLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.loginUserDTOs.DeliveryResponseLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.registerUserDTOs.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.registerUserDTOs.DeliveryResponseRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.UniversalUser;
import org.skyhigh.msskyhighrmm.service.UniversalUserService;
import org.skyhigh.msskyhighrmm.validation.SpringAspect.annotationsApi.ValidParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class UniversalUserController {
    private static final Logger log = Logger.getLogger(UniversalUserController.class.getName());

    private final UniversalUserService universalUserService;

    @Autowired
    public UniversalUserController(UniversalUserService universalUserService) {
        this.universalUserService = universalUserService;
    }

    //project logic - comment it if you wanna just only test the project availability and try the part below
    @ValidParams
    @PostMapping(value = "/users")
    public ResponseEntity<?> registerUser(@RequestBody DeliveryRequestRegisterUserDTO registerUserDTO) {
        log.info("Registering process for '" + registerUserDTO.getLogin() + "' started");

        final UUID registered_user_id = universalUserService.registerUser(registerUserDTO);

        return registered_user_id != null
                ? new ResponseEntity<>(new DeliveryResponseRegisterUserDTO(registered_user_id),
                    HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    2,
                    "Ошибка регистрации",
                    400,
                    "Пользователь с таким логином уже существует."
                    ), HttpStatus.BAD_REQUEST);
    }

    @ValidParams
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody DeliveryRequestLoginUserDTO loginUserDTO) {
        log.info("Login process for '" + loginUserDTO.getLogin() + "' started");

        final String login = loginUserDTO.getLogin();
        final UUID id = universalUserService.checkUser(login);

        if (id == null) {
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    3,
                    "Ошибка авторизации",
                    400,
                    "Данного пользователя не существует"
            ), HttpStatus.BAD_REQUEST);
        }

        return loginUserDTO.getPassword().equals(universalUserService.getUserById(id).getPassword())
                ? new ResponseEntity<>(new DeliveryResponseLoginUserDTO(login, id,
                    "Авторизация пользователя прошла успешно."), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    4,
                    "Ошибка авторизации",
                    400,
                    "Неправильный пароль"), HttpStatus.BAD_REQUEST);
    }

    //при одновременном использовании @ValidParams и параметров в url возникает ошибка,
    // т.к. ValidParams не умеет проверять параметры такого рода (необходима доработка)
    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "user_id") UUID searchForUserId, @ValidParams
                                         @RequestBody DeliveryRequestGetUserByIdDTO getUserByIdDTO) {
        log.info("Getting user by Id '" + searchForUserId +
                "' process started by '" + getUserByIdDTO.getUserMadeRequestId() + "'");

        universalUserService.registerUser(new DeliveryRequestRegisterUserDTO("s", "s"));//to del
        final UUID userMadeRequestId = getUserByIdDTO.getUserMadeRequestId();
        final UniversalUser foundUniversalUser;

        if (universalUserService.getUserById(userMadeRequestId) == null) {
            return new ResponseEntity<>(new CommonExceptionResponseDTO(
                    5,
                    "Ошибка прав доступа",
                    400,
                    "Пользователь, инициировавший операцию, не найден."
            ), HttpStatus.BAD_REQUEST);
        }

        foundUniversalUser = universalUserService.getUserById(searchForUserId);

        return foundUniversalUser != null
                ? new ResponseEntity<>(new DeliveryResponseGetUserByIdDTO("Пользователь найден.",
                    foundUniversalUser), HttpStatus.OK)
                : new ResponseEntity<>(new CommonExceptionResponseDTO(
                    6,
                    "Ошибка выполнения поиска пользователя по идентификатору.",
                    400,
                    "Искомый пользователь не найден."
                    ), HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UniversalUser>> read() {
        final List<UniversalUser> users = universalUserService.readAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

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
