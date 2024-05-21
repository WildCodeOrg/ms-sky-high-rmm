package org.skyhigh.msskyhighrmm;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.skyhigh.msskyhighrmm.controller.RMMControllerTest;
import org.skyhigh.msskyhighrmm.service.UniversalUserService.UniversalUserServiceImplTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RMMControllerTest.class,
        UniversalUserServiceImplTest.class
})
class MsSkyHighRmmApplicationTests {
}
