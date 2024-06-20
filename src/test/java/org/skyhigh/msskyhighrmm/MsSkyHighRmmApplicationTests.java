package org.skyhigh.msskyhighrmm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.skyhigh.msskyhighrmm.controller.RMMControllerTest;
import org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator.EndpointValidatorTest;
import org.skyhigh.msskyhighrmm.service.UniversalUserService.UniversalUserServiceImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RMMControllerTest.class,
        UniversalUserServiceImplTest.class,
        EndpointValidatorTest.class,
})
class MsSkyHighRmmApplicationTests {
}
