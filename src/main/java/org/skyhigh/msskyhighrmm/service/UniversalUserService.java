package org.skyhigh.msskyhighrmm.service;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.ListOfUniversalUser;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.UniversalUser;
import org.skyhigh.msskyhighrmm.model.SystemObjects.UniversalUser.Filters.UniversalUserFilters;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Pagination;
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
     * @param pagination - параметры пагинации (номер запрашиваемой страницы/выборки/набора)
     * @param universalUserFilters - параметры фильтрации (зависят от параметров юзера -
     *      причина блокировки, ФИО, возраст и т.п.)
     * @return список юзеров, удовлетворяющих условиям поиска
     */
    ListOfUniversalUser searchUsers(Pagination pagination, UniversalUserFilters universalUserFilters, UniversalUserSort universalUserSort);

    /**
     * Возвращает список всех имеющихся юзеров
     * @return список юзеров
     */
    List<UniversalUser> readAll();


    /**
     * Обновляет юзера с заданным ID,
     * в соответствии с переданным юзером
     * @param universal_user - юзер в соответсвии с которым нужно обновить данные
     * @param id - id юзера которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(UniversalUser universal_user, UUID id);

    /**
     * Удаляет юзера с заданным ID
     * @param id - id юзера, которого нужно удалить
     * @return - true если юзер был удален, иначе false
     */
    boolean delete(UUID id);

    /**
     * Создает нового клиента
     * @param universal_user - клиент для создания
     */
    void create(UniversalUser universal_user);
}
