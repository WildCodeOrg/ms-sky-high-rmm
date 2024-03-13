package org.skyhigh.msskyhighrmm.service.RolesService;

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
}
