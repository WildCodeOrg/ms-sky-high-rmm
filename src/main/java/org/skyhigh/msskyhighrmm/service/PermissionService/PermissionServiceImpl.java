package org.skyhigh.msskyhighrmm.service.PermissionService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Permissions.ListOfOperationPermissions;
import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.CreatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.DeletePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.UpdatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidationType;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.ValidationResult;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Filters.OperationPermissionFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Sort.OperationPermissionSort;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginatedObject;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidator.validate;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final OperationPermissionsRepository operationPermissionsRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final UserGroupRolesRepository userGroupRolesRepository;
    private final RolesOperationsRepository rolesOperationsRepository;
    private final UniversalUserRepository universalUserRepository;
    private final UserPermissionRepository userPermissionRepository;

    private final EndpointValidator endpointValidator = new EndpointValidator();

    public PermissionServiceImpl(
            OperationPermissionsRepository operationPermissionsRepository,
            UsersRolesRepository usersRolesRepository,
            UserGroupRolesRepository userGroupRolesRepository,
            UserPermissionRepository userPermissionRepository,
            RolesOperationsRepository rolesOperationsRepository,
            UniversalUserRepository universalUserRepository
    ) {
        this.operationPermissionsRepository = operationPermissionsRepository;
        this.usersRolesRepository = usersRolesRepository;
        this.userGroupRolesRepository = userGroupRolesRepository;
        this.rolesOperationsRepository = rolesOperationsRepository;
        this.universalUserRepository = universalUserRepository;
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    public CreatePermissionResultMessage createPermission(
            UUID userMadeRequestId,
            String permissionName,
            String permissionEndpoint
    ) {
        //перенести в конфигурируемые параметры (yml), переменные окружения или сделать через НСИ
        int maxPermissionNameLength = 255;
        int maxEndpointLength = 350;
        int minPermissionNameLength = 10;
        int minEndpointLength = 10;


        if (!universalUserRepository.existsById(userMadeRequestId))
            return new CreatePermissionResultMessage(
                    null,
                    "Пользователь, инициировавший операцию, не найден",
                    1
            );

        if (permissionName == null || permissionEndpoint == null)
            return new CreatePermissionResultMessage(
                    null,
                    "Должны быть заполнены следующие атрибуты разрешения: ['permissionName', 'permissionEndpoint']",
                    7
            );

        if (permissionName.length() > maxPermissionNameLength)
            return new CreatePermissionResultMessage(
                    null,
                    "Длина наименования разрешения не должна быть более  " + maxPermissionNameLength + " символов",
                    2
            );

        if (permissionName.length() < minPermissionNameLength)
            return new CreatePermissionResultMessage(
                    null,
                    "Длина наименования разрешения должна быть не менее " + minPermissionNameLength + " символов",
                    5
            );

        if (permissionEndpoint.length() > maxEndpointLength)
            return new CreatePermissionResultMessage(
                    null,
                    "Длина эндпоинта не должна быть более " + maxEndpointLength + " символов",
                    3
            );

        if (permissionEndpoint.length() < minEndpointLength)
            return new CreatePermissionResultMessage(
                    null,
                    "Длина эндпоинта должна быть не менее " + minEndpointLength + " символов",
                    6
            );

        ValidationResult endpointFormatValidationResult = validate(permissionEndpoint, EndpointValidationType.FULL);

        if (!endpointFormatValidationResult.equals(ValidationResult.SUCCESS))
            return new CreatePermissionResultMessage(
                    null,
                    "Эндпоинт разрешения не соответствует формату '{http_method}:\\{service_name}\\api\\{endpoint}'. Тип ошибки формата: " + endpointFormatValidationResult.toString(),
                    4
            );

        List<OperationPermissionsEntity> operationPermissionsEntitiesWithSameName = operationPermissionsRepository
                .findByPermissionName(permissionName);

        if (operationPermissionsEntitiesWithSameName != null && !operationPermissionsEntitiesWithSameName.isEmpty())
            return new CreatePermissionResultMessage(
                    null,
                    "Разрешение с указанным именем уже существует",
                    8
            );

        List<OperationPermissionsEntity> operationPermissionsEntitiesWithSameEndpoint = operationPermissionsRepository
                .findByEndpoint(permissionEndpoint);

        if (operationPermissionsEntitiesWithSameEndpoint != null && !operationPermissionsEntitiesWithSameEndpoint.isEmpty())
            return new CreatePermissionResultMessage(
                    null,
                    "Разрешение на указанный эндпоинт уже существует",
                    9
            );

        OperationPermissionsEntity operationPermissionsEntity = new OperationPermissionsEntity();
        operationPermissionsEntity.setPermission_name(permissionName);
        operationPermissionsEntity.setOperation_endpoint(permissionEndpoint);

        UUID permissionId = operationPermissionsRepository.save(operationPermissionsEntity).getId();

        return new CreatePermissionResultMessage(
                permissionId,
                "Разрешение успешно сохранено",
                0
        );
    }

    @Override
    public UpdatePermissionResultMessage updatePermission(UUID userMadeRequestId, UUID permissionId, String permissionName, String permissionEndpoint) {
        //перенести в конфигурируемые параметры (yml), переменные окружения или сделать через НСИ
        int maxPermissionNameLength = 255;
        int maxEndpointLength = 350;
        int minPermissionNameLength = 10;
        int minEndpointLength = 10;


        if (!universalUserRepository.existsById(userMadeRequestId))
            return new UpdatePermissionResultMessage(
                    "Пользователь, инициировавший операцию, не найден",
                    1
            );

        Optional<OperationPermissionsEntity> operationPermissionsEntityOptional = operationPermissionsRepository.findById(permissionId);
        if (operationPermissionsEntityOptional.isEmpty())
            return new UpdatePermissionResultMessage(
                    "Разрешение с указанным permissionId не существует",
                    9
            );

        OperationPermissionsEntity updatingPermission = operationPermissionsEntityOptional.get();
        if (updatingPermission.isCritical())
            return new UpdatePermissionResultMessage(
                    "Указанное разрешение - критичное, обновление недоступно",
                    8
            );

        if (permissionName == null && permissionEndpoint == null)
            return new UpdatePermissionResultMessage(
                    "Должен быть заполнен хотя бы один из следующих атрибутов разрешения: ['permissionName', 'permissionEndpoint']",
                    7
            );

        if (permissionName != null) {
            if (permissionName.length() > maxPermissionNameLength)
                return new UpdatePermissionResultMessage(
                        "Длина наименования разрешения не должна быть более  " + maxPermissionNameLength + " символов",
                        2
                );

            if (permissionName.length() < minPermissionNameLength)
                return new UpdatePermissionResultMessage(
                        "Длина наименования разрешения должна быть не менее " + minPermissionNameLength + " символов",
                        5
                );
        }
        if (permissionEndpoint != null) {
            if (permissionEndpoint.length() > maxEndpointLength)
                return new UpdatePermissionResultMessage(
                        "Длина эндпоинта не должна быть более " + maxEndpointLength + " символов",
                        3
                );

            if (permissionEndpoint.length() < minEndpointLength)
                return new UpdatePermissionResultMessage(
                        "Длина эндпоинта должна быть не менее " + minEndpointLength + " символов",
                        6
                );

            ValidationResult endpointFormatValidationResult = validate(permissionEndpoint, EndpointValidationType.FULL);

            if (!endpointFormatValidationResult.equals(ValidationResult.SUCCESS))
                return new UpdatePermissionResultMessage(
                        "Эндпоинт разрешения не соответствует формату '{http_method}:\\{service_name}\\api\\{endpoint}'. Тип ошибки формата: " + endpointFormatValidationResult.toString(),
                        4
                );
        }

        if (permissionName != null)
            updatingPermission.setPermission_name(permissionName);
        if (permissionEndpoint != null)
            updatingPermission.setOperation_endpoint(permissionEndpoint);

        operationPermissionsRepository.save(updatingPermission);
        return new UpdatePermissionResultMessage(
                "Указанное разрешение успешно обновлено",
                0
        );
    }

    @Override
    public ListOfOperationPermissions permissionSearch(PaginationInfo paginationInfo, UUID permissionId, OperationPermissionFilters operationPermissionFilters, OperationPermissionSort operationPermissionSort) {
        ArrayList<OperationPermissionsEntity> resultPermissionList = new ArrayList<>();

        if (permissionId != null) {
            if (operationPermissionsRepository.existsById(permissionId)) {
                resultPermissionList.add(
                        operationPermissionsRepository.getReferenceById(permissionId)
                );
            } else return null;
        } else if (operationPermissionFilters != null) {
            resultPermissionList = OperationPermissionFilters.filter(
                    operationPermissionFilters.getPermissionName(),
                    operationPermissionFilters.getPermissionEndpoint(),
                    operationPermissionFilters.getOperationCriticality(),
                    operationPermissionsRepository
            );
        } else {
            resultPermissionList = (ArrayList<OperationPermissionsEntity>) operationPermissionsRepository.findAll();
        }

        if (resultPermissionList == null) return null;

        if (operationPermissionSort != null) {
            OperationPermissionSort.sort(resultPermissionList, operationPermissionSort);
        }

        int paginationItemCount = resultPermissionList.size();
        int paginationPageNumber = 1;
        int itemCount = resultPermissionList.size();

        if (paginationInfo != null) {
            paginationItemCount = paginationInfo.getRequestedItemCount();
            paginationPageNumber = paginationInfo.getPage();

            PaginatedObject<OperationPermissionsEntity> paginated = new PaginatedObject<>(paginationItemCount,
                    paginationPageNumber, resultPermissionList);
            resultPermissionList = paginated.getResultList();
        }

        return resultPermissionList != null
                ? new ListOfOperationPermissions(itemCount, paginationItemCount, paginationPageNumber, resultPermissionList)
                : null;
    }

    @Override
    public DeletePermissionResultMessage deletePermission(UUID userMadeRequestId, UUID permissionId) {
        if (userMadeRequestId == null || !universalUserRepository.existsById(userMadeRequestId))
            return new DeletePermissionResultMessage(
                    1,
                    "Пользователь, инициировавший выполнение операции, не найден"
            );

        Optional<OperationPermissionsEntity> deletingPermissionOptional = operationPermissionsRepository.findById(permissionId);
        if (deletingPermissionOptional.isEmpty())
            return new DeletePermissionResultMessage(
                    2,
                    "Разрешение по указанному идентификатору не найдено"
            );

        if (deletingPermissionOptional.get().isCritical())
            return new DeletePermissionResultMessage(
                    3,
                    "Разрешение является критичным - удаление невозможно"
            );

        //List<RolesOperationsEntity> relatedRolesOperationsReferences = rolesOperationsRepository.findByPermissionId(permissionId);
        //List<UserPermissionEntity> relatedUserPermissionReferences = userPermissionRepository.findByPermissionId(permissionId);
        //написать транзакцию для удаления связей с ролями и пользователями, а также самого разрешения (возможно через каскад)

        operationPermissionsRepository.delete(deletingPermissionOptional.get());

        return new DeletePermissionResultMessage(
                0,
                "Разрешение успешно удалено"
        );
    }
}
