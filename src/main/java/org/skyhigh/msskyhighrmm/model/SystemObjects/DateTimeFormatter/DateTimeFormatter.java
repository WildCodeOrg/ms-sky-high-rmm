package org.skyhigh.msskyhighrmm.model.SystemObjects.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeFormatter {
    public static String getCurrentDateTimeStringWithTZ() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTimeWithTZ = dateFormatGmt.format(new Date());
        dateTimeWithTZ = dateTimeWithTZ.replace(' ', 'T');
        dateTimeWithTZ += 'Z';

        return dateTimeWithTZ;
    }
}
