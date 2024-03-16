package org.skyhigh.msskyhighrmm.service.UniversalUserService;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UserInfo.UserInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalPagination.PaginationInfo;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Sort.UniversalUserSort;

import java.util.List;
import java.util.UUID;

public interface UniversalUserService {

    /**
     * Создает нового юзера
     * @param login - логин юзера для создания
     * @param password - пароль юзера для создания
     * @return - значение UUID созданной записи юзера
     */
    UUID registerUser(String login, String password);

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
    ListOfUniversalUser searchUsers(PaginationInfo paginationInfo, UniversalUserFilters universalUserFilters, UniversalUserSort universalUserSort);

    /**
     * Обновляет данные о пользователе по его id и возвращает обновленный объект юзера
     * @param userId - идентификатор юзера, данные о котором необходимо обновить
     * @param newUserInfoAttributes - объект с новыми значениями полей иноформации о юзере
     * @return обновленный объект юзера
     */
    UniversalUser updateUserById(UUID userId, UserInfo newUserInfoAttributes);

    /**
     * Возвращает список всех имеющихся юзеров
     * @return список юзеров
     */
    List<UniversalUser> readAll();
}
