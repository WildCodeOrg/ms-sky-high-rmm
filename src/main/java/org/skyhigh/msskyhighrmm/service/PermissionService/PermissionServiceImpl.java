package org.skyhigh.msskyhighrmm.service.PermissionService;

import org.skyhigh.msskyhighrmm.model.DBEntities.OperationPermissionsEntity;
import org.skyhigh.msskyhighrmm.model.DBEntities.UniversalUserEntity;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.CreatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidationType;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.ValidationResult;
import org.skyhigh.msskyhighrmm.repository.*;
import org.skyhigh.msskyhighrmm.service.UniversalUserService.UniversalUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidator.validate;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final OperationPermissionsRepository operationPermissionsRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final UserGroupRolesRepository userGroupRolesRepository;
    private final RolesOperationsRepository rolesOperationsRepository;
    private final UniversalUserRepository universalUserRepository;

    private final EndpointValidator endpointValidator = new EndpointValidator();

    public PermissionServiceImpl(
            OperationPermissionsRepository operationPermissionsRepository,
            UsersRolesRepository usersRolesRepository,
            UserGroupRolesRepository userGroupRolesRepository,
            RolesOperationsRepository rolesOperationsRepository,
            UniversalUserService universalUserService,
            UniversalUserRepository universalUserRepository
    ) {
        this.operationPermissionsRepository = operationPermissionsRepository;
        this.usersRolesRepository = usersRolesRepository;
        this.userGroupRolesRepository = userGroupRolesRepository;
        this.rolesOperationsRepository = rolesOperationsRepository;
        this.universalUserRepository = universalUserRepository;
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
}
