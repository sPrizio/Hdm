package com.smhl.hdm.utils;

import com.smhl.hdm.constants.CoreConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class containing utility methods for the entire system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class HdmUtils {

    private HdmUtils() {
        throw new UnsupportedOperationException(CoreConstants.NO_INSTANTIATION);
    }


    //  METHODS

    /**
     * Returns the season string for the given date time
     *
     * @param dateTime date & time that we want to identify the season string
     * @return season string for the given date
     */
    public static String getSeasonStringForLocalDateTime(LocalDateTime dateTime) {

        if (dateTime.getMonthValue() < 9) {
            return (dateTime.getYear() - 1) + "-" + dateTime.getYear();
        } else {
            return dateTime.getYear() + "-" + (dateTime.getYear() + 1);
        }
    }

    /**
     * Returns the string representing the current season. Basically returns 2018-2019 if the current date is during that season
     *
     * @return season string for current date
     */
    public static String getCurrentSeasonString() {

        LocalDate today = CoreConstants.NOW_AS_LOCALDATE;

        //  we consider the start of a new season to be in September
        if (today.getMonthValue() < 9) {
            return (today.getYear() - 1) + "-" + today.getYear();
        } else {
            return today.getYear() + "-" + (today.getYear() + 1);
        }
    }

    /**
     * Represents the date string of the season with the offset from the current date (in years)
     *
     * @param yearOffset years off set of current season
     * @return season string for offset
     */
    public static String getCurrentSeasonString(Integer yearOffset) {

        LocalDate today = CoreConstants.NOW_AS_LOCALDATE.plusYears(yearOffset);

        //  we consider the start of a new season to be in September
        if (today.getMonthValue() < 9) {
            return (today.getYear() - 1) + "-" + today.getYear();
        } else {
            return today.getYear() + "-" + (today.getYear() + 1);
        }
    }

    /**
     * Parses a date time for the given format
     * @param date date we're looking at
     * @param format date format
     * @return local date time of format
     */
    public static LocalDateTime parse(String date, String format) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format, CoreConstants.HDM_LOCALE));
    }
}
