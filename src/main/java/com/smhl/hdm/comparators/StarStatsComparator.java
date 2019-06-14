package com.smhl.hdm.comparators;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.service.nonentities.star.StarStats;

import java.util.Comparator;

/**
 * Comparator for sorting star stats
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class StarStatsComparator {

    private StarStatsComparator() {
        throw new UnsupportedOperationException(CoreConstants.NO_INSTANTIATION);
    }

    /**
     * Compares star stats by their score
     *
     * @return result of comparison
     */
    public static Comparator<StarStats> compare() {
        return Comparator.comparing(StarStats::getScore);
    }
}
