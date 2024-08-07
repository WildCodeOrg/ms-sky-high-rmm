package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.AddPermissions.AddPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.GetRolePermissions.GetRolePermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions.UnassignPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UpdateRole.UpdateRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;

import java.util.List;
import java.util.UUID;

public interface RolesService {

    /**
     * Принимает параметры создаваемой роли, создаёт и сохраняет роль (некритическую) в Системе,
     *      а также возвращает идентификатор созданной роли
     * @param roleName Наименование создаваемой роли
     * @param description Описание создаваемой роли
     * @return Идентификатор созданной роли в Системе
     */
    UUID addRole(String roleName, String description);

    /**
     * Возвращает страницу/выборку/набор существующих ролей в соответствии с
     *      параметрами фильтрации и пагинации
     * @param paginationInfo Параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param roleId Идентификатор искомой роли (при вводе данного параметра - возвращает 1 объект в списке)
     * @param userGroupRolesFilters Параметры фильтрации (зависят от параметров роли -
     *      название роли, описание роли, критичность роли)
     * @param userGroupRolesSort Параметры сортировки (направление сортировки и атрибут,
     *      по которому необходимо отсортировать список ответов)
     * @return список ролей, удовлетворяющих условиям поиска
     */
    ListOfUserGroupRoles rolesSearch(PaginationInfo paginationInfo, UUID roleId, UserGroupRolesFilters userGroupRolesFilters,
                                     UserGroupRolesSort userGroupRolesSort);

    /**
     * Удаляет некритическую роль из Системы по идентификатору (в т.ч. удаляет её из ролей пользователей)
     *      и возвращает результат выполнения операции (сообщение + код)
     * @param roleId Идентификатор удаляемой записи
     * @return Объект класса DeleteUserGroupRoleResultMessage, содержащий поле message - сообщения о
     *      результате выполнения операции, operationCode - код результата выполнения операции:
     *      0 - успешное выполнение, 1 - ошибка из-за отсутствия записи в системе,
     *      2 - ошибка в связи с попыткой удаления критичной роли из системы
     */
    DeleteUserGroupRoleResultMessage deleteRole(UUID roleId);

    /**
     * Обновляет параметры роли в системе и возвращает результат выполнения операции (сообщение + код)
     *
     * @param userMadeRequestId ID пользователя, инициировавшего операцию;
     * @param roleId ID роли, параметры которой необходимо обновить;
     * @param roleName Новое наименование роли;
     * @param description Новое описание роли;
     * @return Объект класса UpdateRoleResultMessage, содержащий поле message - сообщение о
     *      результате выполнения операции, globalOperationCode - код результата выполнения операции:
     *      0 - упешное выполнение;
     *      1 - пользователь, инициировавший операцию, не найден;
     *      2 - в Системе уже существует роль с именем, идентичным переданному в запросе;
     *      3 - роль является критической, обновление невозможно;
     *      4 - длина наименования роли должна быть больше 0;
     *      5 - должно быть заполнено хотя бы одно из полей [roleName, description];
     *      6 - роли с указанным ID не существует.
     */
    UpdateRoleResultMessage updateRole(UUID userMadeRequestId, UUID roleId, String roleName, String description);

    /**
     * Добавляет указанной роли переданные разрешения и возвращает результат выполнения операции (сообщение + код + список результатов по разрешениям)
     *
     * @param userMadeRequestId ID пользователя, инициировавшего операцию;
     * @param roleId ID роли, для которой необходимо добавить разрешения;
     * @param permissionIds Перечень ID разрешений, которые необходимо добавить для роли;
     * @return Объект класса AddPermissionsResultMessage, содержащий поле message - сообщение о
     *      результате выполнения операции, globalOperationCode - код результата выполнения операции:
     *          0 - упешное выполнение;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - роль с указанным roleId не существует;
     *          3 - роль является критической, обновление списка связанных разрешений невозможно;
     *          4 - список ID добавляемых роли разрешений permissionIds должен содержать не менее 1 записи;
     *          5 - для указанной роли была добавлена лишь часть разрешений из переданного списка в связи с ошибками валидации разрешений;
     *          6 - не удалость добавить ни одно из переданных разрешений для указанной роли в связи с ошибками валидации разрешений;
     *      а также список (List) сообщений результата добавления конкретного разрешения типа AddPermissionsResultMessageListElement с полями:
     *          permissionId - идентификатор добавляемого разрешения;
     *          code - код результата добавления указанного разрешения роли:
     *              0 - успешное выполнение;
     *              1 - разрешение с указанным permissionId не существует;
     *              2 - разрешение с указанным permissionId уже назначено данной роли;
     *          message - сообщение о результате добавления указанного разрешения роли.
     */
    AddPermissionsResultMessage addPermissions(UUID userMadeRequestId, UUID roleId, List<UUID> permissionIds);

    /**
     * Получение списка привязанных к роли разрешений
     * @param userMadeRequestId ID пользователя, инициировавшего операцию;
     * @param roleId ID роли, разрешения которой необходимо найти
     * @return Объект класса GetRolePermissionsResultMessage, содержащий поля:
     *      globalOperationCode - код результата выполнения операции:
     *          0 - успешное выполнение операции;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - роль с указанным roleId не существует;
     *          3 - роль с указанным roleId не содержит привязанных разрешений;
     *      message - сообщение о результате выполнения операции;
     *      List<OperationPermissionEntity> - список найденных разрешений, привязанных роли.
     */
    GetRolePermissionsResultMessage getRolePermissions(UUID userMadeRequestId, UUID roleId);

    /**
     * Отвязывание списка привязанных к роли разрешений
     * @param userMadeRequestId ID пользователя, инициировавшего операцию;
     * @param roleId ID роли, разрешения которой необходимо отвязать
     * @param permissionIds Список идентификаторов отвязываемых от роли разрешений
     * @return Объект класса UnassignPermissionsResultMessage, содержащий поля:
     *      globalOperationCode - код результата выполнения операции:
     *          0 - успешное выполнение операции;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - роль с указанным roleId не существует;
     *          3 - список идентификаторов permissionIds должен быть заполнен хотя бы одним значением;
     *          4 - роль с указанным roleId является критичной - удаление привязанных разрешений невозможно;
     *          5 - от указанной роли была отвязана лишь часть разрешений по причине возникновения ошибок;
     *          6 - от указанной роли не было отвязано ни одно из разрешений по причине возникновения ошибок;
     *      message - сообщение о результате выполнения операции;
     *      messages - список результатов выполнения отвязывания по разрешениям. Содержит объекты класса UnassignPermissionsResultMessageListElement:
     *          permissionId - идентификатор отвязываемого разрешения;
     *          code - код результата отвязывания разрешения. Возможные значения:
     *              0 - успешное удаление связи роль-разрешение;
     *              1 - разрешение с указанным идентификатором не существует;
     *              2 - разрешение с указанным идентификатором не привязано к данной роли;
     *          message - сообщение о результате выполнения операции;
     */
    UnassignPermissionsResultMessage unassignPermissions(UUID userMadeRequestId, UUID roleId, List<UUID> permissionIds);
}