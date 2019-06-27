package com.smhl.hdm.models.nonentities.star;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.Details;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.Objects;

/**
 * A class meant to hold numerical insights regarding a participant's statistical output in a match compared to the others
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class StarStats<D extends Details> implements Comparable<StarStats> {

    @Getter
    private D details;

    @Getter
    private Map<String, DoubleSummaryStatistics> statistics;

    @Getter
    private Double score;

    StarStats(D details, Map<String, DoubleSummaryStatistics> statistics) {
        this.details = details;
        this.statistics = statistics;
        this.score =  calculateScore();
    }


    //  METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        StarStats starStats = (StarStats) o;
        return
                details.getId().equals(starStats.details.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.score);
    }

    @Override
    public int compareTo(StarStats o) {
        return this.score.compareTo(o.score);
    }


    //  HELPERS

    /**
     * Calculates the star's score, which refers an internal ranking that we use to assign the participant's statistical reference in a game
     *
     * @return numerical relevance of a participant in a game
     */
    private Double calculateScore() {

        this.score = 0.0;

        if (this.details instanceof SkaterGameDetails) {
            this.score += calculateVariance(((SkaterGameDetails) this.details).getGoals(), this.statistics.get("goals").getAverage()) * CoreConstants.GOAL_RANK_MULTIPLIER;
            this.score += calculateVariance(((SkaterGameDetails) this.details).getAssists(), this.statistics.get("assists").getAverage()) * CoreConstants.ASSIST_RANK_MULTIPLIER;
            this.score += calculateVariance(((SkaterGameDetails) this.details).getShots(), this.statistics.get("shots").getAverage()) * CoreConstants.SHOT_RANK_MULTIPLIER;
            this.score += calculateVariance(((SkaterGameDetails) this.details).getBlockedShots(), this.statistics.get("blockedShots").getAverage()) * CoreConstants.BLOCKED_SHOT_RANK_MULTIPLIER;
        }

        return this.score;
    }

    /**
     * Calculates the percentage change between two number inputs
     *
     * @param xi - skater  performance metric (goals, assists...)
     * @param xf - game averages
     * @return decimal value of the percentage change, 0 if the change is negative
     */
    private Double calculateVariance(Number xi, Number xf) {
        Double initial = xi.doubleValue();
        Double finalNum = xf.doubleValue();

        if ((initial - finalNum) < 0) {
            return 0.0;
        }

        return (initial - finalNum) / finalNum;
    }
}
