package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.AddPermissions.AddPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
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
     * @param roleName - наименование создаваемой роли
     * @param description - описание создаваемой роли
     * @return - идентификатор созданной роли в Системе
     */
    UUID addRole(String roleName, String description);

    /**
     * Возвращает страницу/выборку/набор существующих ролей в соответствии с
     *      параметрами фильтрации и пагинации
     * @param paginationInfo - параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param roleId - идентификатор искомой роли (при вводе данного параметра - возвращает 1 объект в списке)
     * @param userGroupRolesFilters - параметры фильтрации (зависят от параметров роли -
     *      название роли, описание роли, критичность роли)
     * @param userGroupRolesSort - параметры сортировки (направление сортировки и атрибут,
     *      по которому необходимо отсортировать список ответов)
     * @return список ролей, удовлетворяющих условиям поиска
     */
    ListOfUserGroupRoles rolesSearch(PaginationInfo paginationInfo, UUID roleId, UserGroupRolesFilters userGroupRolesFilters,
                                     UserGroupRolesSort userGroupRolesSort);

    /**
     * Удаляет некритическую роль из Системы по идентификатору (в т.ч. удаляет её из ролей пользователей)
     *      и возвращает результат выполнения операции (сообщение + код)
     * @param roleId - идентификатор удаляемой записи
     * @return - объект класса DeleteUserGroupRoleResultMessage, содержащий поле message - сообщения о
     *      результате выполнения операции, operationCode - код результата выполнения операции:
     *      0 - успешное выполнение, 1 - ошибка из-за отсутствия записи в системе,
     *      2 - ошибка в связи с попыткой удаления критичной роли из системы
     */
    DeleteUserGroupRoleResultMessage deleteRole(UUID roleId);

    /**
     * Обновляет параметры роли в системе и возвращает результат выполнения операции (сообщение + код)
     *
     * @param userMadeRequestId - ID пользователя, инициировавшего операцию;
     * @param roleId - ID роли, параметры которой необходимо обновить;
     * @param roleName - новое наименование роли;
     * @param description - новое описание роли;
     * @return - Объект класса UpdateRoleResultMessage, содержащий поле message - сообщение о
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
     * @param userMadeRequestId - ID пользователя, инициировавшего операцию;
     * @param roleId - ID роли, для которой необходимо добавить разрешения;
     * @param permissionIds - перечень ID разрешений, которые необходимо добавить для роли;
     * @return - Объект класса AddPermissionsResultMessage, содержащий поле message - сообщение о
     *      результате выполнения операции, globalOperationCode - код результата выполнения операции:
     *          0 - упешное выполнение;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - роль с указанным roleId не существует;
     *          3 - роль является критической, обновление списка связанных разрешений невозможно;
     *          4 - спиок ID добавляемых роли разрешений permissionIds должен содержать не менее 1 записи;
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
}