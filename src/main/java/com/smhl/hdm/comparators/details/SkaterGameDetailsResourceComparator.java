package com.smhl.hdm.comparators.details;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.resources.details.participant.SkaterGameDetailsResource;

import java.util.Comparator;

/**
 * Custom comparator for use with SkaterGameDetails. When comparing a list of skater game details, we want them to be sorted by the Team ID,
 * followed by the Point total, followed by the Goal total, followed by the Assist total
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class SkaterGameDetailsResourceComparator {

    private SkaterGameDetailsResourceComparator() {
        throw new UnsupportedOperationException(CoreConstants.NO_INSTANTIATION);
    }


    //  METHODS

    public static Comparator<SkaterGameDetailsResource> sort() {

        Comparator<SkaterGameDetailsResource> byTeam =
                (det1, det2) -> det1.getTeam().getCode().compareTo(det2.getCode());

        Comparator<SkaterGameDetailsResource> byPoints =
                Comparator.comparing(SkaterGameDetailsResource::getPoints);

        Comparator<SkaterGameDetailsResource> byGoals =
                Comparator.comparing(SkaterGameDetailsResource::getGoals);

        Comparator<SkaterGameDetailsResource> byAssists =
                Comparator.comparing(SkaterGameDetailsResource::getAssists);

        return
                byPoints
                        .thenComparing(byGoals)
                        .thenComparing(byAssists)
                        .thenComparing(byTeam)
                        .reversed();
    }
}
