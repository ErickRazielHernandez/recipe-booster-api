package org.rzlindustries.mx.utils;

import java.time.ZoneId;

public class BsConstants {
    private BsConstants() {
        super();
    }

    public static final String FORMAT = "json";
    public static final String WSTOKEN = "wstoken";
    public static final String WSFUNCTION = "wsfunction";
    public static final String MOODLEWSRESTFORMAT = "moodlewsrestformat";
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Mexico_City");
    public static final String LOCAL_DATE_FORMAT = "dd/MM/yyyy";
    public static final String LOCAL_TIME_FORMAT = "HH:mm:ss";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
}
