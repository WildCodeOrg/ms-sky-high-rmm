package org.skyhigh.msskyhighrmm.model.SystemObjects.DateTimeFormatter;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class DateTimeFormatterTest {
    private static final Logger log = Logger.getLogger(DateTimeFormatterTest.class.getName());

    @Test
    public void getCurrentDateTimeStringWithTZTestSuccess() {
        String res = DateTimeFormatter.getCurrentDateTimeStringWithTZ();
        //log.info(res);

        boolean containsTZ = res.contains("T") && res.contains("Z");

        Assertions.assertTrue(containsTZ);
        Assertions.assertEquals(24, res.length());

        res = res.replace('T', ' ');
        res = res.substring(0, res.length() - 1);

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        String finalRes = res;
        Assertions.assertDoesNotThrow(
                () -> {
                    Date date = dateFormatGmt.parse(finalRes);
                    Assertions.assertEquals(finalRes, dateFormatGmt.format(date));
                }
        );
    }
}
