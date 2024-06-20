package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser.LoginUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RegisterUser.RegisterUserResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.SortDirection;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSortParameter;
import org.skyhigh.msskyhighrmm.repository.AdministratorKeyCodeRepository;
import org.skyhigh.msskyhighrmm.repository.BlockReasonsRepository;
import org.skyhigh.msskyhighrmm.repository.UniversalUserRepository;
import org.skyhigh.msskyhighrmm.repository.UsersRolesRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UniversalUserServiceImplTest {
    @InjectMocks
    private UniversalUserServiceImpl universalUserService;

    @Mock
    private UniversalUserRepository universalUserRepository;

    @Mock
    private BlockReasonsRepository blockReasonsRepository;

    @Mock
    private AdministratorKeyCodeRepository administratorKeyCodeRepository;

    @Mock
    private UsersRolesRepository usersRolesRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private final PasswordEncoder passwordEncoderTest = new BCryptPasswordEncoder();

    @Test
    public void registerUserTest_Success() {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        boolean isAdmin = false;
        String adminKey = null;
        UUID testId = UUID.randomUUID();

        UniversalUserEntity universalUserEntityTestWithId = new UniversalUserEntity();
        universalUserEntityTestWithId.setLogin(testLogin);
        universalUserEntityTestWithId.setPassword(passwordEncoderTest.encode(testPassword));
        universalUserEntityTestWithId.setId(testId);

        UniversalUserEntity universalUserEntityTestWithoutId = new UniversalUserEntity();
        universalUserEntityTestWithoutId.setLogin(testLogin);
        universalUserEntityTestWithoutId.setPassword(passwordEncoderTest.encode(testPassword));


        List<UniversalUserEntity> universalUserEntitiesTest = new ArrayList<>();

        Mockito.when(passwordEncoder.encode(testPassword))
                .thenReturn(passwordEncoderTest.encode(testPassword));
        Mockito.when(universalUserRepository.findByLogin(testLogin))
                .thenReturn(universalUserEntitiesTest);
        Mockito.when(universalUserRepository.save(universalUserEntityTestWithoutId))
                .thenReturn(universalUserEntityTestWithId);

        RegisterUserResultMessage result = universalUserService.registerUser(testLogin, testPassword, isAdmin, adminKey);
        Assertions.assertEquals("User created successfully.", result.getGlobalMessage());
        Assertions.assertEquals(0, result.getGlobalOperationCode());
        Assertions.assertEquals(testId, result.getCreatedUserId());
    }

    @Test
    public void loginUserTest_Success() {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        boolean isAdmin = false;
        String adminKey = null;
        UUID testId = UUID.randomUUID();

        UniversalUserEntity universalUserEntityTestWithId = new UniversalUserEntity();
        universalUserEntityTestWithId.setLogin(testLogin);
        universalUserEntityTestWithId.setPassword(passwordEncoderTest.encode(testPassword));
        universalUserEntityTestWithId.setId(testId);

        List<UniversalUserEntity> universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntityTestWithId);

        Mockito.when(universalUserRepository.findByLogin(testLogin))
                .thenReturn(universalUserEntitiesTest);
        Mockito.when(passwordEncoder.matches(testPassword, universalUserEntityTestWithId.getPassword())).
                thenReturn(true);

        LoginUserResultMessage result = universalUserService.loginUser(testLogin, testPassword);
        Assertions.assertEquals(0, result.getGlobalOperationCode());
        Assertions.assertNull(result.getGlobalMessage());
        Assertions.assertEquals(testId, result.getLogonUserId());
    }

    @Test
    public void searchUsersTest_Success_NoFilters_NoPagination_NoSort() {
        UUID user1Id = UUID.randomUUID();
        UUID user2Id = UUID.randomUUID();
        String testPassword = "testPassword";

        UniversalUserEntity universalUserEntity1 = new UniversalUserEntity(
                user1Id,
                "TestUser1",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        UniversalUserEntity universalUserEntity2 = new UniversalUserEntity(
                user2Id,
                "TestUser2",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        List<UniversalUserEntity> universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntity1);
        universalUserEntitiesTest.add(universalUserEntity2);

        Mockito.when(universalUserRepository.findAll())
                .thenReturn(universalUserEntitiesTest);

        ListOfUniversalUser result = universalUserService.searchUsers(null, null, null);
        Assertions.assertEquals(result.getItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPaginationItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPageNumber(), 1);
        Assertions.assertEquals(result.getUniversalUsers().size(), universalUserEntitiesTest.size());
        for (int i = 0; i < result.getUniversalUsers().size(); i++) {
            Assertions.assertEquals(result.getUniversalUsers().get(i).getId(), universalUserEntitiesTest.get(i).getId());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getLogin(), universalUserEntitiesTest.get(i).getLogin());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getPassword(), universalUserEntitiesTest.get(i).getPassword());
        }
    }

    @Test
    public void searchUsersTest_Success_WithFilters_NoPagination_NoSort() {
        UUID user1Id = UUID.randomUUID();
        UUID user2Id = UUID.randomUUID();
        String testPassword = "testPassword";

        UniversalUserEntity universalUserEntity1 = new UniversalUserEntity(
                user1Id,
                "TestUser1",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        UniversalUserEntity universalUserEntity2 = new UniversalUserEntity(
                user2Id,
                "TestUser2",
                passwordEncoderTest.encode(testPassword),
                new UserInfo("Alex", null, 0),
                null
        );

        List<UniversalUserEntity> universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntity1);
        universalUserEntitiesTest.add(universalUserEntity2);

        Mockito.when(universalUserRepository.findAll())
                .thenReturn(universalUserEntitiesTest);

        universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntity2);
        Mockito.when(universalUserRepository.findByFirstName(universalUserEntity2.getUser_info().getFirstName()))
                .thenReturn(universalUserEntitiesTest);

        ListOfUniversalUser result = universalUserService.searchUsers(
                null,
                new UniversalUserFilters(null, new UserInfo("Alex", null, 0)),
                null
        );

        Assertions.assertEquals(result.getItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPaginationItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPageNumber(), 1);
        Assertions.assertEquals(result.getUniversalUsers().size(), universalUserEntitiesTest.size());
        for (int i = 0; i < result.getUniversalUsers().size(); i++) {
            Assertions.assertEquals(result.getUniversalUsers().get(i).getId(), universalUserEntitiesTest.get(i).getId());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getLogin(), universalUserEntitiesTest.get(i).getLogin());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getPassword(), universalUserEntitiesTest.get(i).getPassword());
            Assertions.assertNotNull(result.getUniversalUsers().get(i).getUser_info());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getUser_info().getFirstName(), universalUserEntitiesTest.get(i).getUser_info().getFirstName());
        }
    }

    @Test
    public void searchUsersTest_Success_NoFilters_WithPagination_NoSort() {
        UUID user1Id = UUID.randomUUID();
        UUID user2Id = UUID.randomUUID();
        String testPassword = "testPassword";

        UniversalUserEntity universalUserEntity1 = new UniversalUserEntity(
                user1Id,
                "TestUser1",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        UniversalUserEntity universalUserEntity2 = new UniversalUserEntity(
                user2Id,
                "TestUser2",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        List<UniversalUserEntity> universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntity1);
        universalUserEntitiesTest.add(universalUserEntity2);

        Mockito.when(universalUserRepository.findAll())
                .thenReturn(universalUserEntitiesTest);

        ListOfUniversalUser result = universalUserService.searchUsers(
                new PaginationInfo(1, 1),
                null,
                null
        );

        Assertions.assertEquals(result.getItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPaginationItemCount(), 1);
        Assertions.assertEquals(result.getPageNumber(), 1);
        Assertions.assertEquals(result.getUniversalUsers().size(), 1);
        for (int i = 0; i < result.getUniversalUsers().size(); i++) {
            Assertions.assertEquals(result.getUniversalUsers().get(i).getId(), universalUserEntitiesTest.get(i).getId());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getLogin(), universalUserEntitiesTest.get(i).getLogin());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getPassword(), universalUserEntitiesTest.get(i).getPassword());
        }
    }

    @Test
    public void searchUsersTest_Success_NoFilters_NoPagination_WithSort() {
        UUID user1Id = UUID.randomUUID();
        UUID user2Id = UUID.randomUUID();
        String testPassword = "testPassword";

        UniversalUserEntity universalUserEntity1 = new UniversalUserEntity(
                user1Id,
                "TestUser1",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        UniversalUserEntity universalUserEntity2 = new UniversalUserEntity(
                user2Id,
                "TestUser2",
                passwordEncoderTest.encode(testPassword),
                null,
                null
        );

        List<UniversalUserEntity> universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntity1);
        universalUserEntitiesTest.add(universalUserEntity2);

        Mockito.when(universalUserRepository.findAll())
                .thenReturn(universalUserEntitiesTest);

        universalUserEntitiesTest = new ArrayList<>();
        universalUserEntitiesTest.add(universalUserEntity2);
        universalUserEntitiesTest.add(universalUserEntity1);

        ListOfUniversalUser result = universalUserService.searchUsers(null, null, new UniversalUserSort(SortDirection.DESC, UniversalUserSortParameter.LOGIN));
        Assertions.assertEquals(result.getItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPaginationItemCount(), universalUserEntitiesTest.size());
        Assertions.assertEquals(result.getPageNumber(), 1);
        Assertions.assertEquals(result.getUniversalUsers().size(), universalUserEntitiesTest.size());
        for (int i = 0; i < result.getUniversalUsers().size(); i++) {
            Assertions.assertEquals(result.getUniversalUsers().get(i).getId(), universalUserEntitiesTest.get(i).getId());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getLogin(), universalUserEntitiesTest.get(i).getLogin());
            Assertions.assertEquals(result.getUniversalUsers().get(i).getPassword(), universalUserEntitiesTest.get(i).getPassword());
        }
    }

    @Test
    public void updateUserByIdTest() {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        boolean isAdmin = false;
        String adminKey = null;
        UUID testId = UUID.randomUUID();

        UniversalUserEntity universalUserEntityTestWithId = new UniversalUserEntity();
        universalUserEntityTestWithId.setLogin(testLogin);
        universalUserEntityTestWithId.setPassword(passwordEncoderTest.encode(testPassword));
        universalUserEntityTestWithId.setId(testId);

        Mockito.when(universalUserRepository.existsById(testId))
                .thenReturn(true);

        UserInfo newUserInfoAttributes = new UserInfo();
        newUserInfoAttributes.setFirstName("Alex");
        universalUserEntityTestWithId.setUser_info(newUserInfoAttributes);

        Mockito.doNothing().when(universalUserRepository)
                .updateUserInfoForUserWithId(testId, newUserInfoAttributes);

        Mockito.when(universalUserRepository.findById(testId))
                .thenReturn(Optional.of(universalUserEntityTestWithId));

        UniversalUser result = universalUserService.updateUserById(testId, newUserInfoAttributes);
        Assertions.assertEquals(result.getId(), testId);
        Assertions.assertEquals(result.getLogin(), testLogin);
        Assertions.assertEquals(result.getPassword(), universalUserEntityTestWithId.getPassword());
        Assertions.assertNotNull(result.getUser_info());
        Assertions.assertEquals(result.getUser_info().getFirstName(), newUserInfoAttributes.getFirstName());
        Assertions.assertNull(result.getUser_info().getSecondName());
        Assertions.assertEquals(result.getUser_info().getAge(), 0);
        Assertions.assertNull(result.getBlock_reason_id());
    }

    @Test
    public void blockUsersTest() {

    }

    @Test
    public void addAdminKeyTest() {

    }

    @Test
    public void readAllTest() {

    }
}
