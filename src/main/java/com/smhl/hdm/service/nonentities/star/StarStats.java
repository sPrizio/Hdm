package com.smhl.hdm.service.nonentities.star;

import com.smhl.hdm.models.entities.details.Details;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Double score;

    StarStats(D details, Double goalsPercentage, Double assistsPercentage, Double shotsPercentage, Double blockedShotsPercentage) {
        this.details = details;
        this.score =  calculateScore(goalsPercentage, assistsPercentage, shotsPercentage, blockedShotsPercentage);
    }

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

    private Double calculateScore(Double goalsPercentage, Double assistsPercentage, Double shotsPercentage, Double blockedShotsPercentage) {
        return goalsPercentage + assistsPercentage + shotsPercentage + blockedShotsPercentage;
    }
}
