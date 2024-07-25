package org.skyhigh.msskyhighrmm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.getUserByIdDTOs.DeliveryRequestGetUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.loginUserDTOs.DeliveryRequestLoginUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.registerUserDTOs.DeliveryRequestRegisterUserDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.searchUsersDTOs.DeliveryRequestSearchUsersDTO;
import org.skyhigh.msskyhighrmm.model.DTO.universalUserRMMControllerDTOs.updateUserByIdDTOs.DeliveryRequestUpdateUserByIdDTO;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser.LoginUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RegisterUser.RegisterUserResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;
import org.skyhigh.msskyhighrmm.service.UniversalUserService.UniversalUserService;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class RMMControllerTest {
    @Mock
    private UniversalUserService universalUserService;

    @InjectMocks
    private RMMController rmmController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rmmController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void registerUserTest_SimpleUser_Success() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12345678",
                false,
                null
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);
        UUID testUserId = UUID.randomUUID();

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                    new RegisterUserResultMessage(
                        "User created successfully.",
                        0,
                        testUserId));

        mockMvc.perform(post("/rmm-service/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registeredUserId").value(testUserId.toString()));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_SimpleUser_LoginLengthException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "Alex",
                "12345678",
                false,
                null
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "Login length must be in the range from 6 to 20 characters.",
                                1,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10001"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("Длина логина должна быть от 6 до 20 символов."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_SimpleUser_PasswordLengthException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12",
                false,
                null
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "Password length must be in the range from 8 to 20 characters.",
                                2,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10002"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("Длина пароля должна быть от 8 до 20 символов."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_SimpleUser_UserAlreadyExistsException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12345678",
                false,
                null
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "User with this id already exists.",
                                3,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10003"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("Пользователь с таким логином уже существует."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_AdminUser_AdminKeyIsEmptyException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12345678",
                true,
                null
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "AdminKey cannot be null for admins.",
                                4,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10004"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("При регистрации администратора заполнение ключа администатора является обязательным."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_AdminUser_AdminKeySizeException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12345678",
                true,
                "1"
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "AdminKey must be size of 32.",
                                5,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10005"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("Длина ключа администратора 32 символа."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_AdminUser_AdminKeyNotExistException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12345678",
                true,
                "7d0deb4d-a449-457c-a1bf-dbdf019a"
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "There is no such admin key code for admins.",
                                6,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10006"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("Указанного ключа администратора не существует."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void registerUserTest_AdminUser_AdminWithCertainKeyAlreadyExistsException() throws Exception {
        DeliveryRequestRegisterUserDTO deliveryRequestRegisterUserDTO = new DeliveryRequestRegisterUserDTO(
                "AlexMan1",
                "12345678",
                true,
                "7d0deb4d-a449-457c-a1bf-dbdf019t"
        );
        String deliveryRequestRegisterUserDTOJson = objectMapper.writeValueAsString(deliveryRequestRegisterUserDTO);

        when(universalUserService.registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()))
                .thenReturn(
                        new RegisterUserResultMessage(
                                "Admin with certain admin key code already exists.",
                                7,
                                null));

        mockMvc.perform(post("/rmm-service/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestRegisterUserDTOJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("10007"))
                .andExpect(jsonPath("$.description").value("Ошибка регистрации."))
                .andExpect(jsonPath("$.httpCode").value("400"))
                .andExpect(jsonPath("$.message").value("Указанный ключ администратора уже используется другим пользователем."));

        verify(universalUserService, times(1)).registerUser(
                deliveryRequestRegisterUserDTO.getLogin(),
                deliveryRequestRegisterUserDTO.getPassword(),
                deliveryRequestRegisterUserDTO.isAdminRegistration(),
                deliveryRequestRegisterUserDTO.getAdminKey()
        );
    }

    @Test
    public void loginUserTest_Success() throws Exception {
        DeliveryRequestLoginUserDTO deliveryRequestLoginUserDTO = new DeliveryRequestLoginUserDTO(
                "AlexMan1",
                "12345678"
        );
        String deliveryRequestLoginUserDTOJson = objectMapper.writeValueAsString(deliveryRequestLoginUserDTO);
        UUID testUserId = UUID.randomUUID();

        when(universalUserService.loginUser(
                deliveryRequestLoginUserDTO.getLogin(),
                deliveryRequestLoginUserDTO.getPassword()))
                .thenReturn(
                        new LoginUserResultMessage(
                                null,
                                0,
                                testUserId));

        mockMvc.perform(post("/rmm-service/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestLoginUserDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(deliveryRequestLoginUserDTO.getLogin()))
                .andExpect(jsonPath("$.id").value(testUserId.toString()))
                .andExpect(jsonPath("$.message").value("Пользователь успешно авторизован."));

        verify(universalUserService, times(1)).loginUser(
                deliveryRequestLoginUserDTO.getLogin(),
                deliveryRequestLoginUserDTO.getPassword()
        );
    }

    @Test
    public void getUserByIdTest_Success() throws Exception {
        UUID testUserMadeRequestId = UUID.randomUUID();
        UUID s1Id = UUID.randomUUID();

        DeliveryRequestGetUserByIdDTO deliveryRequestGetUserByIdDTO = new DeliveryRequestGetUserByIdDTO(
                testUserMadeRequestId
        );

        String deliveryRequestGetUserByIdDTOJson = objectMapper.writeValueAsString(deliveryRequestGetUserByIdDTO);
        UUID testUserId = UUID.randomUUID();
        UniversalUser foundUniversalUserTest = new UniversalUser();
        foundUniversalUserTest.setId(testUserId);
        foundUniversalUserTest.setSecretId(s1Id);

        when(universalUserService.getUserById(
                testUserMadeRequestId)).thenReturn(foundUniversalUserTest);
        when(universalUserService.getUserById(
                testUserId)).thenReturn(foundUniversalUserTest);

        mockMvc.perform(get("/rmm-service/api/users/{user_id}", testUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestGetUserByIdDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Пользователь найден."))
                .andExpect(jsonPath("$.foundUniversalUser.id").value(foundUniversalUserTest.getId().toString()))
                .andExpect(jsonPath("$.foundUniversalUser.secretId").value(foundUniversalUserTest.getSecretId().toString()))
                .andExpect(jsonPath("$.foundUniversalUser.user_info").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.foundUniversalUser.block_reason_id").value(IsNull.nullValue()));

        verify(universalUserService, times(1)).getUserById(
                testUserId
        );
        verify(universalUserService, times(1)).getUserById(
                testUserMadeRequestId
        );
    }

    @Test
    public void searchUsersTest_Success() throws Exception {
        UUID testUserMadeRequestId = UUID.randomUUID();
        UUID s1Id = UUID.randomUUID();

        DeliveryRequestSearchUsersDTO deliveryRequestSearchUsersDTO = new DeliveryRequestSearchUsersDTO(
                new PaginationInfo(1, 5),
                testUserMadeRequestId,
                new UniversalUserFilters(null, new UserInfo("Alex", null, 0)),
                new UniversalUserSort("ASC", "LOGIN")
        );

        String deliveryRequestSearchUsersDTOJson = objectMapper.writeValueAsString(deliveryRequestSearchUsersDTO);
        UUID testUserId = UUID.randomUUID();

        UniversalUser foundUniversalUserTest = new UniversalUser();
        foundUniversalUserTest.setId(testUserId);
        foundUniversalUserTest.setSecretId(s1Id);
        foundUniversalUserTest.setUser_info(new UserInfo("Alex", null, 0));
        foundUniversalUserTest.setBlock_reason_id(null);

        ListOfUniversalUser resultListOfUniversalUserTest = new ListOfUniversalUser();
        resultListOfUniversalUserTest.setItemCount(1);
        resultListOfUniversalUserTest.setPaginationItemCount(5);
        resultListOfUniversalUserTest.setPageNumber(1);
        List<UniversalUser> universalUserList = new ArrayList<>();
        universalUserList.add(foundUniversalUserTest);
        resultListOfUniversalUserTest.setUniversalUsers(universalUserList);

        when(universalUserService.getUserById(
                testUserMadeRequestId)).thenReturn(foundUniversalUserTest);
        when(universalUserService.searchUsers(
                deliveryRequestSearchUsersDTO.getPagination(),
                deliveryRequestSearchUsersDTO.getFilters(),
                deliveryRequestSearchUsersDTO.getSort()
                ))
                .thenReturn(resultListOfUniversalUserTest);

        mockMvc.perform(post("/rmm-service/api/users/search", testUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryRequestSearchUsersDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foundItemAmount").value("1"))
                .andExpect(jsonPath("$.pageInfo.pageNumber").value(resultListOfUniversalUserTest.getPageNumber()))
                .andExpect(jsonPath("$.pageInfo.onPageItemCount").value(resultListOfUniversalUserTest.getPaginationItemCount()))
                .andExpect(jsonPath("$.universalUsers[0].id").value(foundUniversalUserTest.getId().toString()))
                .andExpect(jsonPath("$.universalUsers[0].secretId").value(foundUniversalUserTest.getSecretId().toString()))
                .andExpect(jsonPath("$.universalUsers[0].block_reason_id").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.universalUsers[0].user_info.firstName").value(foundUniversalUserTest.getUser_info().getFirstName()))
                .andExpect(jsonPath("$.universalUsers[0].user_info.secondName").value(foundUniversalUserTest.getUser_info().getSecondName()))
                .andExpect(jsonPath("$.universalUsers[0].user_info.age").value(Integer.toString(foundUniversalUserTest.getUser_info().getAge())));

        verify(universalUserService, times(1)).getUserById(
                testUserMadeRequestId
        );
        verify(universalUserService, times(1)).searchUsers(
                deliveryRequestSearchUsersDTO.getPagination(),
                deliveryRequestSearchUsersDTO.getFilters(),
                deliveryRequestSearchUsersDTO.getSort()
        );
    }

    @Test
    public void updateUserByIdTest_Success() throws Exception {
        UUID testUserMadeRequestId = UUID.randomUUID();
        UUID s1Id = UUID.randomUUID();

        DeliveryRequestUpdateUserByIdDTO deliveryRequestUpdateUserByIdDTO = new DeliveryRequestUpdateUserByIdDTO(
                testUserMadeRequestId,
                new UserInfo("Hello", "man", 2)
        );

        String deliveryRequestUpdateUserByIdDTOJson = objectMapper.writeValueAsString(deliveryRequestUpdateUserByIdDTO);
        UUID testUserId = UUID.randomUUID();
        UniversalUser foundUniversalUserTest = new UniversalUser();
        foundUniversalUserTest.setId(testUserId);
        foundUniversalUserTest.setSecretId(s1Id);

        when(universalUserService.getUserById(
                testUserMadeRequestId)).thenReturn(foundUniversalUserTest);
        when(universalUserService.getUserById(
                testUserId)).thenReturn(foundUniversalUserTest);
        foundUniversalUserTest.setUser_info(deliveryRequestUpdateUserByIdDTO.getNewUserInfoAttributes());
        when(universalUserService.updateUserById(
                testUserId, deliveryRequestUpdateUserByIdDTO.getNewUserInfoAttributes()))
                .thenReturn(foundUniversalUserTest);

        mockMvc.perform(put("/rmm-service/api/users/{user_id}", testUserId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(deliveryRequestUpdateUserByIdDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Запись пользователя успешно обновлена"))
                .andExpect(jsonPath("$.updatedUniversalUser.id").value(foundUniversalUserTest.getId().toString()))
                .andExpect(jsonPath("$.updatedUniversalUser.secretId").value(foundUniversalUserTest.getSecretId().toString()))
                .andExpect(jsonPath("$.updatedUniversalUser.user_info").value(deliveryRequestUpdateUserByIdDTO.getNewUserInfoAttributes()))
                .andExpect(jsonPath("$.updatedUniversalUser.block_reason_id").value(IsNull.nullValue()));

        verify(universalUserService, times(1)).getUserById(
                testUserMadeRequestId
        );
        verify(universalUserService, times(1)).getUserById(
                testUserId
        );
        verify(universalUserService, times(1)).updateUserById(
                testUserId,
                deliveryRequestUpdateUserByIdDTO.getNewUserInfoAttributes()
        );
    }

    @Test
    public void blockUsersTest_Success() throws Exception {

    }

    @Test
    public void addUserGroupRoleTest_Success() throws Exception {

    }

    @Test
    public void searchRolesTest_Success() throws Exception {

    }

    @Test
    public void deleteRoleTest_Success() throws Exception {

    }

    @Test
    public void addAdminKeyCode_Success() throws Exception {

    }
}
