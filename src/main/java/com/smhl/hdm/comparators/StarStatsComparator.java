package com.smhl.hdm.comparators;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.nonentities.star.StarStats;

import java.util.Comparator;

/**
 * Comparator for sorting star stats. We sort stars by their 'score' which is an attribute that stores an algorithmic computation
 * of a participant's statistical relevance in a game. The more they contributed, the higher their score will be and we want to sort
 * by this value
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class StarStatsComparator {

    private StarStatsComparator() {
        throw new UnsupportedOperationException(CoreConstants.NO_INSTANTIATION);
    }


    //  METHODS

    /**
     * Compares star stats by their score
     *
     * @return result of comparison
     */
    public static Comparator<StarStats> compare() {
        return Comparator.comparing(StarStats::getScore);
    }
}
