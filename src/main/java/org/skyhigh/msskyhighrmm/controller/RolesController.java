package org.skyhigh.msskyhighrmm.controller;

import lombok.Getter;
import org.skyhigh.msskyhighrmm.service.RolesService.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Getter
@RestController
@RequestMapping("/roles")
public class RolesController {
    private static final Logger log = Logger.getLogger(UniversalUserController.class.getName());

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }
}
