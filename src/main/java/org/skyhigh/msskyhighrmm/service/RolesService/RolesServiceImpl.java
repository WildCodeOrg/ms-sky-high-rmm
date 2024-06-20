package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.UserGroupRole;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.RolesOperationsEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UserGroupRolesEntity;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.AddPermissions.AddPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.AddPermissions.AddPermissionsResultMessageListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.GetRolePermissions.GetRolePermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UpdateRole.UpdateRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginatedObject;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Converters.RoleEntityToRoleBOConverter;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;
import org.skyhigh.msskyhighrmm.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class RolesServiceImpl implements RolesService{
    private static final Logger log = Logger.getLogger(RolesServiceImpl.class.getName());

    private final UserGroupRolesRepository userGroupRolesRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final RolesOperationsRepository rolesOperationsRepository;
    private final UniversalUserRepository universalUserRepository;
    private final OperationPermissionsRepository operationPermissionsRepository;

    public RolesServiceImpl(UserGroupRolesRepository userGroupRolesRepository, UsersRolesRepository usersRolesRepository, RolesOperationsRepository rolesOperationsRepository, UniversalUserRepository universalUserRepository, OperationPermissionsRepository operationPermissionsRepository) {
        this.userGroupRolesRepository = userGroupRolesRepository;
        this.usersRolesRepository = usersRolesRepository;
        this.rolesOperationsRepository = rolesOperationsRepository;
        this.universalUserRepository = universalUserRepository;
        this.operationPermissionsRepository = operationPermissionsRepository;
    }

    //Добавление новой роли
    @Override
    public UUID addRole(String roleName, String description)
    {
        List<UserGroupRolesEntity> rolesWithSameName = userGroupRolesRepository.findByRoleName(roleName);
        if (rolesWithSameName != null && !rolesWithSameName.isEmpty()) return null;

        return (userGroupRolesRepository
                .save(new UserGroupRolesEntity(
                        null,
                        roleName,
                        description,
                        false)
                )
        ).getId();
    }

    //Параметризированный поиск ролей
    @Override
    public ListOfUserGroupRoles rolesSearch(PaginationInfo paginationInfo, UUID roleId, UserGroupRolesFilters userGroupRolesFilters, UserGroupRolesSort userGroupRolesSort) {
        ArrayList<UserGroupRole> resultUserGroupRolesList = new ArrayList<>();

        if (roleId != null) {
            if (userGroupRolesRepository.existsById(roleId)) {
                resultUserGroupRolesList.add(
                        RoleEntityToRoleBOConverter.convert(userGroupRolesRepository.getReferenceById(roleId))
                );
            } else return null;
        } else if (userGroupRolesFilters != null) {
            resultUserGroupRolesList = UserGroupRolesFilters.filter(
                    userGroupRolesFilters.getRoleName(),
                    userGroupRolesFilters.getDescription(),
                    userGroupRolesFilters.getCriticality(),
                    userGroupRolesRepository
            );
        } else {
            resultUserGroupRolesList = (ArrayList<UserGroupRole>) RoleEntityToRoleBOConverter.convertList(userGroupRolesRepository.findAll());
        }

        if (resultUserGroupRolesList == null) return null;

        if (userGroupRolesSort != null) {
            UserGroupRolesSort.sort(resultUserGroupRolesList, userGroupRolesSort);
        }

        int paginationItemCount = resultUserGroupRolesList.size();
        int paginationPageNumber = 1;
        int itemCount = resultUserGroupRolesList.size();

        if (paginationInfo != null) {
            paginationItemCount = paginationInfo.getRequestedItemCount();
            paginationPageNumber = paginationInfo.getPage();

            PaginatedObject<UserGroupRole> paginated = new PaginatedObject<>(paginationItemCount,
                    paginationPageNumber, resultUserGroupRolesList);
            resultUserGroupRolesList = paginated.getResultList();
        }

        return resultUserGroupRolesList != null
            ? new ListOfUserGroupRoles(itemCount, paginationItemCount, paginationPageNumber, resultUserGroupRolesList)
            : null;
    }

    @Override
    public DeleteUserGroupRoleResultMessage deleteRole(UUID roleId) {
        if (!userGroupRolesRepository.existsById(roleId))
            return new DeleteUserGroupRoleResultMessage(
                    "Роли с идентификатором '" + roleId + "' не сущетсвует",
                    1
            );
        if (getRoleById(roleId).isCritical())
            return new DeleteUserGroupRoleResultMessage(
                    "Роль с идентификатором '" + roleId + "' является критичной",
                    2
            );

        usersRolesRepository.deleteByRoleId(roleId);
        userGroupRolesRepository.deleteById(roleId);

        return new DeleteUserGroupRoleResultMessage(
                "Роль с идентификатором '" + roleId + "' успешно удалена",
                0
        );
    }

    @Override
    public UpdateRoleResultMessage updateRole(UUID userMadeRequestId, UUID roleId, String roleName, String description) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new UpdateRoleResultMessage(
                    1,
                    "Пользователь, инициировавший операцию, не найден"
            );

        if (roleName == null && description == null)
            return new UpdateRoleResultMessage(
                    5,
                    "Должно быть заполнено хотя бы одно из полей: [roleName, description]"
            );

        List<UserGroupRolesEntity> rolesWithSameName = userGroupRolesRepository.findByRoleName(roleName);
        if (rolesWithSameName != null && !rolesWithSameName.isEmpty())
            return new UpdateRoleResultMessage(
                    2,
                    "В Системе уже существует роль с именем, аналогичным переданному в запросе"
            );

        Optional<UserGroupRolesEntity> updatingRoleOptional = userGroupRolesRepository.findById(roleId);
        if (updatingRoleOptional.isEmpty())
            return new UpdateRoleResultMessage(
                    6,
                    "Роль с указанным roleId не существует"
            );

        if (updatingRoleOptional.get().isCritical())
            return new UpdateRoleResultMessage(
                    3,
                    "Роль является критической, обновление невозможно"
            );

        if (roleName != null && roleName.isEmpty())
            return new UpdateRoleResultMessage(
                    4,
                    "Поле roleName должно быть не заполнено или иметь длину больше 0"
            );

        UserGroupRolesEntity updatingRole = updatingRoleOptional.get();
        if (roleName != null)
            updatingRole.setRoleName(roleName);
        if (description != null)
            updatingRole.setDescription(description);

        userGroupRolesRepository.updateRoleAllArgsById(
                roleId,
                updatingRole.getRoleName(),
                updatingRole.getDescription()
        );

        return new UpdateRoleResultMessage(
                0,
                "Параметры роли успешно обновлены"
        );
    }

    @Override
    public AddPermissionsResultMessage addPermissions(UUID userMadeRequestId, UUID roleId, List<UUID> permissionIds) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new AddPermissionsResultMessage(
                    1,
                    "Пользователь, инициировавший операцию, не найден",
                    null
            );

        Optional<UserGroupRolesEntity> updatingRole = userGroupRolesRepository.findById(roleId);

        if (updatingRole.isEmpty())
            return new AddPermissionsResultMessage(
                    2,
                    "Роль с указанным roleId не существует",
                    null
            );

        if (updatingRole.get().isCritical())
            return new AddPermissionsResultMessage(
                    3,
                    "Роль является критической, обновление списка связанных разрешений невозможно",
                    null
            );

        if (permissionIds.isEmpty())
            return new AddPermissionsResultMessage(
                    4,
                    "Спиок ID добавляемых роли разрешений permissionIds должен содержать не менее 1 запись",
                    null
            );

        AddPermissionsResultMessage result = new AddPermissionsResultMessage();
        List<AddPermissionsResultMessageListElement> addPermissionsList = new ArrayList<>();
        for (UUID permissionId : permissionIds) {
            if (!operationPermissionsRepository.existsById(permissionId))
                addPermissionsList.add(new AddPermissionsResultMessageListElement(
                       permissionId,
                       1,
                       "Разрешение с указанным permissionId не существует"
                ));
            else {
                List<RolesOperationsEntity> existingReferences = rolesOperationsRepository.findByRoleIdAndPermissionId(roleId, permissionId);
                if (existingReferences != null && !existingReferences.isEmpty())
                    addPermissionsList.add(new AddPermissionsResultMessageListElement(
                            permissionId,
                            2,
                            "Разрешение с указанным permissionId уже назначено данной роли"
                    ));
                else {
                    RolesOperationsEntity rolesOperationsEntity = new RolesOperationsEntity();
                    rolesOperationsEntity.setRoleId(userGroupRolesRepository.getReferenceById(roleId));
                    rolesOperationsEntity.setPermissionId(operationPermissionsRepository.getReferenceById(permissionId));

                    UUID createdReferenceId = rolesOperationsRepository.save(rolesOperationsEntity).getId();
                    addPermissionsList.add(new AddPermissionsResultMessageListElement(
                            permissionId,
                            0,
                            "Разрешение успешно добавлено для указанной роли"
                    ));
                    log.info("A reference in table operation_permissions was created with parameters:" +
                            "{id: '" + createdReferenceId + "', role_id: '" + roleId + "', permission_id: '" + permissionId + "'}");
                }
            }
        }

        int unsuccessfullyAddPermissionAmount = 0;
        for (AddPermissionsResultMessageListElement addPermissionsResultMessageListElement : addPermissionsList) {
            if (addPermissionsResultMessageListElement.getCode() != 0)
                unsuccessfullyAddPermissionAmount++;
        }

        if (unsuccessfullyAddPermissionAmount == addPermissionsList.size()) {
            result.setGlobalOperationCode(6);
            result.setMessage("Не удалость добавить ни одно из переданных разрешений для указанной роли в связи с ошибками валидации разрешений");
        } else if (unsuccessfullyAddPermissionAmount != 0) {
            result.setGlobalOperationCode(5);
            result.setMessage("Для указанной роли была добавлена лишь часть разрешений из переданного списка в связи с ошибками валидации разрешений");
        } else {
            result.setMessage("Все разрешения были успешно добавлены для указанной роли.");
        }

        result.setResults(addPermissionsList);

        return result;
    }

    @Override
    public GetRolePermissionsResultMessage getRolePermissions(UUID userMadeRequestId, UUID roleId) {
        if (!universalUserRepository.existsById(userMadeRequestId))
            return new GetRolePermissionsResultMessage(
                    1,
                    "Пользователь, инициировавший операцию, не найден",
                    null
            );

        if (!userGroupRolesRepository.existsById(roleId))
            return new GetRolePermissionsResultMessage(
                    2,
                    "Роль с указанным roleId не существует",
                    null
            );

        List<OperationPermissionsEntity> permissionsEntityList = operationPermissionsRepository.findOperationPermissionsByRoleId(
                //userGroupRolesRepository.getReferenceById(roleId)
                roleId
        );

        if (permissionsEntityList == null || permissionsEntityList.isEmpty())
            return new GetRolePermissionsResultMessage(
                    3,
                    "Роль с указанным roleId не содержит привязанных разрешений",
                    null
            );

        return new GetRolePermissionsResultMessage(
                0,
                "Разрешения, привязанные к указанной роли, успешно найдены",
                permissionsEntityList
        );
    }

    private UserGroupRole getRoleById(UUID id) {
        Optional<UserGroupRolesEntity> userGroupRoleOptional = userGroupRolesRepository.findById(id);
        return userGroupRoleOptional.map(RoleEntityToRoleBOConverter::convert).orElse(null);
    }
}
