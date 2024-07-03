package org.skyhigh.msskyhighrmm.service.PermissionService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Permissions.ListOfOperationPermissions;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.CreatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.DeletePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.PermissionServiceMessages.UpdatePermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Filters.OperationPermissionFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.OperationPermission.Sort.OperationPermissionSort;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;

import java.util.UUID;

public interface PermissionService {


    /** Принимает параметры создаваемого разрешения, создает и сохраняет разрешение (некритичное),
     *      а также возвращает идентификатор созданного разрешения, код результата + сообщение
     *
     * @param userMadeRequestId Идентификатор пользователя, инициировавшего операцию;
     * @param permissionName Наименование создаваемого разрешение;
     * @param permissionEndpoint Эндпоинт, на который данное разрешение устанавливается;
     * @return Объект класса CreatePermissionResultMessage, содержащий поля:
     *      permissionId - Идентификатор созданного разрешения (заполнен только если разрешение было успешно сохранено в БД);
     *      message - сообщение о результате выполнения операции;
     *      globalOperationCode - код результата выполнения операции:
     *          0 - успешное сохранение разрешения в системе;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - длина наименования разрешения не должна быть более  %maxPermissionNameLength% символов;
     *          3 - длина эндпоинта не должна быть более  %maxEndpointLength% символов;
     *          4 - эндпоинт разрешения не соответствует формату;
     *          5 - длина наименования разрешения должна быть не менее %minPermissionNameLength% символов;
     *          6 - длина эндпоинта должна быть не менее %minEndpointLength% символов;
     *          7 - должны быть заполнены следующие атрибуты разрешения: ['permissionName', 'permissionEndpoint']
     *          8 - разрешение с указанным именем уже существует;
     *          9 - разрешение на указанный эндпоинт уже существует;
     */
    CreatePermissionResultMessage createPermission(
            UUID userMadeRequestId,
            String permissionName,
            String permissionEndpoint
    );

    /** Принимает параметры разрешения и обновляет разрешение (некритичное),
     *      а также возвращает код результата + сообщение
     *
     * @param userMadeRequestId Идентификатор пользователя, инициировавшего операцию;
     * @param permissionId Идентификатор обновляемого разрешения;
     * @param permissionName Наименование создаваемого разрешение;
     * @param permissionEndpoint Эндпоинт, на который данное разрешение устанавливается;
     * @return Объект класса CreatePermissionResultMessage, содержащий поля:
     *      permissionId - Идентификатор созданного разрешения (заполнен только если разрешение было успешно сохранено в БД);
     *      message - сообщение о результате выполнения операции;
     *      globalOperationCode - код результата выполнения операции:
     *          0 - успешное сохранение разрешения в системе;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - длина наименования разрешения не должна быть более  %maxPermissionNameLength% символов;
     *          3 - длина эндпоинта не должна быть более  %maxEndpointLength% символов;
     *          4 - эндпоинт разрешения не соответствует формату;
     *          5 - длина наименования разрешения должна быть не менее %minPermissionNameLength% символов;
     *          6 - длина эндпоинта должна быть не менее %minEndpointLength% символов;
     *          7 - должен быть заполнен хотя бы один из следующих атрибуты разрешения: ['permissionName', 'permissionEndpoint']
     *          8 - указанное разрешение - критичное, обновление недоступно;
     *          9 - разрешения с указанным permissionId не существует;
     */
    UpdatePermissionResultMessage updatePermission (
            UUID userMadeRequestId,
            UUID permissionId,
            String permissionName,
            String permissionEndpoint
    );

    /**
     * Возвращает страницу/выборку/набор существующих разрешений в соответствии с
     *      параметрами фильтрации и пагинации
     * @param paginationInfo Параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param permissionId Идентификатор искомого разрешения (при вводе данного параметра - возвращает 1 объект в списке)
     * @param operationPermissionFilters Параметры фильтрации (зависят от параметров разрешения -
     *      название разрешения, описание разрешения, критичность разрешения)
     * @param operationPermissionSort Параметры сортировки (направление сортировки и атрибут,
     *      по которому необходимо отсортировать список ответов)
     * @return Список разрешений, удовлетворяющих условиям поиска
     */
    ListOfOperationPermissions permissionSearch(PaginationInfo paginationInfo, UUID permissionId, OperationPermissionFilters operationPermissionFilters,
                                                OperationPermissionSort operationPermissionSort);

    /**
     * Удаляет некритическое разрешение из Системы
     * @param userMadeRequestId Идентификатор пользователя, инициировавшего выполнение операции
     * @param permissionId Идентификатор удаляемого из Системы разрешения
     * @return Объект класса DeletePermissionResultMessage - резуьтат выполнения операции, содержащий поля:
     *      globalOperationCode - код результата выполнения операции, возможные значения которого:
     *          0 - успешное выполнение операции;
     *          1 - пользователь, инициировавший выполнение операции, не найден;
     *          2 - разрешение по указанному идентификатору не найдено;
     *          3 - разрешение является критичным - удаление невозможно;
     *      message - сообщение о результате выполнения операции;
     */
    DeletePermissionResultMessage deletePermission (UUID userMadeRequestId, UUID permissionId);
}
