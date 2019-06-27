package com.smhl.hdm.utils;

import com.smhl.hdm.constants.CoreConstants;

import java.time.LocalDate;

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
}
