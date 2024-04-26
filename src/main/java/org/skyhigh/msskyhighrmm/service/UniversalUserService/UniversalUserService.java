package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UsersToBlockInfoListElement;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.BlockUsers.BlockUsersResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.LoginUser.LoginUserResultMessage;
import org.skyhigh.msskyhighrmm.model.ServiceMethodsResultMessages.UniversalUserServiceMessages.RegisterUser.RegisterUserResultMessage;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface UniversalUserService {

    /**
     * Создает нового юзера
     * @param login - логин юзера для создания
     * @param password - пароль юзера для создания
     * @return - значение UUID созданной записи юзера
     */
    RegisterUserResultMessage registerUser(String login, String password);

    /**
     * Проверяет, есть ли юзер с заданным логином в Системе
     * @param login - логин юзера, по которому осущствляется поиск
     * @return - значение UUID записи юзера, если он был найден,
     * null - если такого юзера не существует
     */
    UUID checkUser(String login);

    /**
     * Возвращает юзера по его ID
     * @param id - ID юзера
     * @return - объект юзера с заданным ID
     */
    UniversalUser getUserById(UUID id);

    /**
     * Возвращает страницу/выборку/набор зарегистрированных юзеров в соответствии с
     *      параметрами фильтрации и пагинации
     * @param paginationInfo - параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param universalUserFilters - параметры фильтрации (зависят от параметров юзера -
     *      причина блокировки, ФИО, возраст и т.п.)
     * @return список юзеров, удовлетворяющих условиям поиска
     */
    ListOfUniversalUser searchUsers(PaginationInfo paginationInfo,
                                    UniversalUserFilters universalUserFilters,
                                    UniversalUserSort universalUserSort);

    /**
     * Обновляет данные о пользователе по его id и возвращает обновленный объект юзера
     * @param userId - идентификатор юзера, данные о котором необходимо обновить
     * @param newUserInfoAttributes - объект с новыми значениями полей иноформации о юзере
     * @return обновленный объект юзера
     */
    UniversalUser updateUserById(UUID userId, UserInfo newUserInfoAttributes);

    /**
     * Выполняет блокировку пользователя/списка пользователей (устанавливает пользователям с
     *      переданными идентификаторами указанный код причины блокировки) и возвращает
     *      результат выполнения операции (глобальные сообщение + код, а также сообщения + код по каждой
     *      переданной связке "id юзера + код причины блокировки")
     * @param usersInfoToBlock - список связок "userId + blockReasonId" (id пользователя, которого нужно
*           заблокировать, а также код причины блокировки)
     * @param userToBlockId - идентификатор пользователя, которого нужно заблокировать (передается либо он,
     *                      либо usersInfoToBlock - при попытке передаче обоих параметров возникает ошибка)
     * @param blockReasonId - код причины блокировки для указанного пользователя (не передается, если указан
     *                      usersInfoToBlock - выбрасывает ошибку)
     * @return - объект BlockUsersResultMessage, имеющий поля:
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
     * @param login - логин пользователя
     * @param password - пароль пользователя
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
     * Возвращает список всех имеющихся юзеров
     * @return список юзеров
     */
    List<UniversalUser> readAll();
}
