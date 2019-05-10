package com.smhl.hdm.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * A class that contains constants used for core functionality
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class CoreConstants {

    private static final ZoneId MONTREAL = ZoneId.of("America/Montreal");

    private CoreConstants() {
        throw new UnsupportedOperationException();
    }

    public static final String NO_INSTANTIATION = "Utility/Constant classes should not be instantiated";
    public static final LocalDate NOW_AS_LOCALDATE = LocalDate.now(MONTREAL);
    public static final LocalDateTime NOW_AS_LOCALDATETIME = LocalDateTime.now(MONTREAL);
}
