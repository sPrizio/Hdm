package com.smhl.hdm.utils;

import com.smhl.hdm.constants.CoreConstants;

/**
 * Utility class for aiding in calculating milestone values
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class MilestoneUtils {

    private MilestoneUtils() {
        throw new UnsupportedOperationException(CoreConstants.NO_INSTANTIATION);
    }

    /**
     * Calculates the next milestone plateau for a given value
     *
     * @param value current value of a stat
     * @return the next interval to hit for a plateau
     */
    public static Integer calculatePlateau(Integer value) {

        if (value < 50) {
            return 50;
        } else if (value < 100) {
            return 100;
        }

        return (value - (value % 100)) + 100;
    }

}
