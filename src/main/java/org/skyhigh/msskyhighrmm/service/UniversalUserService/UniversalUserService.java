package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UsersToBlockInfoListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.RolesServiceMessages.UnassignPermissions.UnassignPermissionsResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddAdminKey.AddAdminKeyResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddBlockReason.AddBlockReasonResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddPermission.UserAddPermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.AddRoleToUser.AddRoleToUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserPermission.GetUserPermissionResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.GetUserRoles.GetUserRolesResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser.LoginUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RegisterUser.RegisterUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RemoveRoleFromUserList.RemoveRoleFromUserListResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface UniversalUserService {

    /**
     * Создает нового юзера
     * @param login Логин юзера для создания
     * @param password Пароль юзера для создания
     * @param isAdmin Признак, является ли регистрирующийся пользователь админом (true - да, false - нет)
     * @param adminKey Ключ-код админа
     * @return RegisterUserResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *         0 - выполнено успешно;
     *         1 - невалидный логин - должен быть от 6 до 20 символов;
     *         2 - невалидный пароль - должен быть от 8 до 20 символов;
     *         3 - указанный логин занят;
     *         4 - ключ-код для админов должен не передан;
     *         5 - ключ-код админа невалиден - должен иметь длину 32 символа;
     *         6 - ключ-код админа не существует;
     *         7 - по указанному ключ-коду админа уже существует зарегистрированный пользователь;
     *      createdUserId - ID зарегистрированного в Системе пользователя.
     */
    RegisterUserResultMessage registerUser(String login, String password, boolean isAdmin, String adminKey);

    /**
     * Проверяет, есть ли юзер с заданным логином в Системе
     * @param login Логин юзера, по которому осущствляется поиск
     * @return Значение UUID записи юзера, если он был найден,
     * null - если такого юзера не существует
     */
    UUID checkUser(String login);

    /**
     * Возвращает юзера по его ID
     * @param id ID юзера
     * @return Объект юзера с заданным ID
     */
    UniversalUser getUserById(UUID id);

    /**
     * Возвращает страницу/выборку/набор зарегистрированных юзеров в соответствии с
     *      параметрами фильтрации и пагинации
     * @param paginationInfo Параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param universalUserFilters Параметры фильтрации (зависят от параметров юзера -
     *      причина блокировки, ФИО, возраст и т.п.)
     * @return Список юзеров, удовлетворяющих условиям поиска
     */
    ListOfUniversalUser searchUsers(PaginationInfo paginationInfo,
                                    UniversalUserFilters universalUserFilters,
                                    UniversalUserSort universalUserSort);

    /**
     * Обновляет данные о пользователе по его id и возвращает обновленный объект юзера
     * @param userId Идентификатор юзера, данные о котором необходимо обновить
     * @param newUserInfoAttributes Объект с новыми значениями полей иноформации о юзере
     * @return Обновленный объект юзера
     */
    UniversalUser updateUserById(UUID userId, UserInfo newUserInfoAttributes);

    /**
     * Выполняет блокировку пользователя/списка пользователей (устанавливает пользователям с
     *      переданными идентификаторами указанный код причины блокировки) и возвращает
     *      результат выполнения операции (глобальные сообщение + код, а также сообщения + код по каждой
     *      переданной связке "id юзера + код причины блокировки")
     * @param usersInfoToBlock Список связок "userId + blockReasonId" (id пользователя, которого нужно
*           заблокировать, а также код причины блокировки)
     * @param userToBlockId Идентификатор пользователя, которого нужно заблокировать (передается либо он,
     *                      либо usersInfoToBlock - при попытке передаче обоих параметров возникает ошибка)
     * @param blockReasonId Код причины блокировки для указанного пользователя (не передается, если указан
     *                      usersInfoToBlock - выбрасывает ошибку)
     * @return Объект BlockUsersResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно для всех переданных связок;
     *          1 - выполнено частично (некоторые связки обработаны успешно, некоторые - нет);
     *          2 - не выполнено ни для одной связки в связи с ошибками.
     *      certainBlockUsersResults - список связок (тип BlockUsersResultMessageListElement)
     *          "id блокируемого пользователя + сообщение + код"
     *          для каждой связки id юзера + код причины блокировки. Возможные значения кода:
     *              0 - выполнено успешно;
     *              1 - юзер по указанному id не найден;
     *              2 - причина блокировки с указанным кодом не найдена.
     */
    BlockUsersResultMessage blockUsers(ArrayList<UsersToBlockInfoListElement> usersInfoToBlock,
                                       UUID userToBlockId, String blockReasonId);

    /**
     * Метод авторизации пользователя в системе
     * @param login Логин пользователя
     * @param password Пароль пользователя
     * @return LoginUserResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно;
     *          1 - пользователя не существует (неверный логин);
     *          2 - неправильный пароль;
     *      logonUserId - ID авторизованного пользователя в Системе.
     */
    LoginUserResultMessage loginUser(String login, String password);


    /**
     * Метод добавления нового ключ-кода админа
     * @param userMadeRequest ID пользователя, инициировавшего операцию (считается,
     *                        что такой пользователь на момент вызова метода существует)
     * @param adminKey Значение ключ-кода
     * @return AddAdminKeyResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно;
     *          1 - невалидный ключ-код;
     *          2 - создающий ключ-код пользователь не является администратором;
     *      adminKeyReferenceId - ID созданной записи с ключ-кодом админа
     */
    AddAdminKeyResultMessage addAdminKey(UUID userMadeRequest, String adminKey);

    /**
     * Выполняет выдачу роли списку пользователей и возвращает
     *      результат выполнения операции (глобальные сообщение + код, а также сообщения + код по каждому
     *      переданному id юзера)
     * @param usersToAddRoleIds Список id юзеров, которым необходимо выдать указанную роль
     * @param userMadeRequestId Идентификатор пользователя, инициировавшего операцию
     * @param roleId - id выдаваемой пользователям роли
     * @return Объект AddRoleToUserResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно для всех переданных id юзеров;
     *          1 - выполнено частично (некоторые id пользователей обработаны успешно, некоторые - нет);
     *          2 - ни одноиму из пользователей роль не была назначена;
     *          3 - пользователь, инициировавший операцию, не найден;
     *          4 - роли с указанным id не существует;
     *          5 - массив id пользователей - пустой;
     *      certainAddRoleToUsersResults - список связок (тип AddRoleToUserResultMessageListElement)
     *          "id пользователя + сообщение + код"
     *          для каждого id юзера. Возможные значения кода:
     *              0 - выполнено успешно;
     *              1 - юзер по указанному id не найден;
     *              2 - у пользователя уже назначена указанная роль.
     */
    AddRoleToUserResultMessage addRoleToUsers(UUID userMadeRequestId, UUID roleId, List<UUID> usersToAddRoleIds);

    /**
     * Выполняет поиск и выдачу ролей пользователя
     * @param userMadeRequestId - идентификатор пользователя, инициировавшего операцию
     * @param userId - id пользователя, роли которого необходимо отобразить;
     * @return Объект GetUserRolesResultMessage, имеющий поля:
     *      globalMessage -  глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - пользователь, роли которого необходимо отобразить, не найден;
     *          3 - у искомого пользователя нет назначенных ролей;
     *      rolesOfUser - список ролей пользователя в виде: id роли (roleId) + наименование роли (roleName).
     */
    GetUserRolesResultMessage getUserRoles(UUID userMadeRequestId, UUID userId);

    /**
     * Выполняет отмену роли списку пользователей и возвращает
     *      результат выполнения операции (глобальные сообщение + код, а также сообщения + код по каждому
     *      переданному id юзера)
     * @param usersToRemoveRoleIds список id юзеров, у которых необходимо отменить указанную роль
     * @param userMadeRequestId идентификатор пользователя, инициировавшего операцию
     * @param roleId id отменяемой у пользователей роли
     * @return Объект AddRoleToUserResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно для всех переданных id юзеров;
     *          1 - выполнено частично (некоторые id пользователей обработаны успешно, некоторые - нет);
     *          2 - ни одноиму из пользователей роль не назначена;
     *          3 - пользователь, инициировавший операцию, не найден;
     *          4 - роли с указанным id не существует;
     *          5 - массив id пользователей - пустой;
     *          6 - отмену критической роли может выполнять только пользователь с правами администратора;
     *          7 - произошла системная ошибка;
     *      certainResultMessages - список связок (тип RemoveRoleFromUserListResultMessageListElement)
     *          "id пользователя + сообщение + код"
     *          для каждого id юзера. Возможные значения кода:
     *              0 - выполнено успешно;
     *              1 - юзер по указанному id не найден;
     *              2 - у пользователя нет указанной роли;
     *              3 - непредвиденная ошибка.
     */
    RemoveRoleFromUserListResultMessage removeRoleFromUserList(UUID userMadeRequestId, UUID roleId, List<UUID> usersToRemoveRoleIds);

    /**
     * Выполняет создание новой причины блокировки в Системе.
     * @param userMadeRequestId идентификатор пользователя, инициировавшего операцию
     * @param blockReasonDescription описание создаваемой причины блокировки
     * @return Объект AddBlockReasonResultMessage, имеющий поля:
     *      globalMessage - глобальное сообщение о результате выполнения операции (строка);
     *      globalOperationCode - глобальный код результата выполнения операции (число):
     *          0 - выполнено успешно;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - непредвиденная ошибка;
     */
    AddBlockReasonResultMessage addBlockReason(UUID userMadeRequestId, String blockReasonDescription);

    /**
     * Выполняет поиск разрешений пользователя в соответствии с фильтром и возвращает найденный список
     * @param userMadeRequestId - идентификатор пользователя, инициировавшего операцию
     * @param userId идентификатор пользователя, разрешения которого необходимо найти
     * @param filter фильтр (перечисление), в соответствии с которым выполняется поиск разрешений пользователя.
     *               Возможные значения проверяются по классу UserPermissionFilter.
     * @return Объект класса GetUserPermissionResultMessage, имеющий поля:
     *      globalOperationCode - код результата выполнения операции (число):
     *          0 - успешное выполнение операции;
     *          1 - пользователь, инициировавший выполнение операции, не найден;
     *          2 - пользователь, для которого выполняется поиск разрешений, не найден;
     *          3 - указан некорректный тип фильтрации. Допустимые значения перечисления 'Тип фильтрации': [%UserPermissionFilter.values%]
     *          4 - у пользователя '%userId%' нет назначенных разрешений, соответствующих переданному критерию фильтрации;
     *      message - сообщение о результате выполнения операции;
     *      userPermissions - коллекция ключ/значение разрешений пользователя (List<OperationPermissionsEntity>),
     *          разределенных на блоки:
     *              ["forceAssigned", "roleBased"]
     */
    GetUserPermissionResultMessage getUserPermission(UUID userMadeRequestId, UUID userId, String filter);

    /**
     * Добавляет разрешения пользователю и возвращает результат выполнения операции (код + сообщение + сообщения с кодами для каждого переданного разрешения)
     * @param userMadeRequestId Идентификатор пользователя, инициировавшего операцию
     * @param userId Идентификатор пользователя, которому назначают разрешения
     * @param permissionIds Список идентификаторов назначаемых пользователю разрешений
     * @return Объект класса UserAddPermissionResultMessage, содержащий поля:
     *      globalOperationCode - код результата выполнения операции, возможные значения которого:
     *          0 - успешное выполнение операции;
     *          1 - пользователь, инициировавший выполнение операции, не найден;
     *          2 - пользователь, для которого выполняется операция назначения разрешений, не найден;
     *          3 - переданный список идентификаторов назначаемых пользователю разрешений не может быть пустым;
     *          4 - успешно назначена пользователю лишь часть из списка переданных разрешений;
     *          5 - ни одно из указанных разрешений не было назначено пользователю;
     *      message - сообщение о результате выполнения операции;
     *      Список объектов UserAddPermissionResultMessageListElement (поля code, permissionId, message) - сообщений о результате
     *          добавления каждого из переданных разрешений;
     *          возможные значения поля code класса UserAddPermissionResultMessageListElement:
     *              0 - разрешение успешно назначено пользователю;
     *              1 - указанное разрешение не существует;
     *              2 - указанное разрешение уже назначено пользователю.
     *
     */
    UserAddPermissionResultMessage userAddPermission(UUID userMadeRequestId, UUID userId, List<UUID> permissionIds);

    /**
     * Отвязывание списка привязанных к пользователю разрешений
     * @param userMadeRequestId ID пользователя, инициировавшего операцию;
     * @param userId ID пользователя, разрешения которого необходимо отвязать
     * @param permissionIds Список идентификаторов отвязываемых от пользователя разрешений
     * @return Объект класса UnassignPermissionsResultMessage, содержащий поля:
     *      globalOperationCode - код результата выполнения операции:
     *          0 - успешное выполнение операции;
     *          1 - пользователь, инициировавший операцию, не найден;
     *          2 - пользователь с указанным userId не существует;
     *          3 - список идентификаторов permissionIds должен быть заполнен хотя бы одним значением;
     *          4 - у указанного пользователя была отозвана лишь часть разрешений по причине возникновения ошибок;
     *          5 - у указанного пользователя не было отозвано ни одно из разрешений по причине возникновения ошибок;
     *      message - сообщение о результате выполнения операции;
     *      messages - список результатов выполнения отвязывания по разрешениям. Содержит объекты класса UnassignPermissionsResultMessageListElement:
     *          permissionId - идентификатор отвязываемого разрешения;
     *          code - код результата отвязывания разрешения. Возможные значения:
     *              0 - успешное удаление связи пользователь-разрешение;
     *              1 - разрешение с указанным идентификатором не существует;
     *              2 - разрешение с указанным идентификатором не привязано к данному пользователю;
     *          message - сообщение о результате выполнения операции;
     */
    UnassignPermissionsResultMessage unassignPermissions(UUID userMadeRequestId, UUID userId, List<UUID> permissionIds);

    /**
     * Возвращает список всех имеющихся юзеров
     * @return список юзеров
     */
    List<UniversalUser> readAll();
}
