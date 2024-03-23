package org.skyhigh.msskyhighrmm.service.RolesService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Roles.ListOfUserGroupRoles;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.DeleteUserGroupRoleResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Filters.UserGroupRolesFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UserGroupRole.Sort.UserGroupRolesSort;

import java.util.UUID;

public interface RolesService {

    /**
     * Принимает параметры создаваемой роли, создаёт и сохраняет роль в Системе,
     *      а также возвращает идентификатор созданной роли
     * @param roleName - наименование создаваемой роли
     * @param description - описание создаваемой роли
     * @param isCritical - признак критичности создаваемой роли
     * @return - идентификатор созданной роли в Системе
     */
    UUID addRole(String roleName, String description, boolean isCritical);

    /**
     * Возвращает страницу/выборку/набор существующих ролей в соответствии с
     *      параметрами фильтрации и пагинации
     * @param paginationInfo - параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param roleId - идентификатор искомой роли (при вводе данного параметра - возвращает 1 объект в списке)
     * @param userGroupRolesFilters - параметры фильтрации (зависят от параметров роли -
     *      название роли, описание роли, критичность роли)
     * @param userGroupRolesSort - параметры сортировки (направление сортировки и атрибут,
     *      по которому необходимо отсортировать список ответов)
     * @return список юзеров, удовлетворяющих условиям поиска
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

}
