package com.smhl.hdm.resources.season;

import com.smhl.hdm.resources.HdmResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * DTO for goalie seasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class GoalieSeasonResource implements HdmResource, Comparable<GoalieSeasonResource> {

    @Getter
    @Setter
    private String seasonString;

    @Getter
    @Setter
    private int gamesPlayed;

    @Getter
    @Setter
    private int gamesStarted;

    @Getter
    @Setter
    private int wins;

    @Getter
    @Setter
    private int losses;

    @Getter
    @Setter
    private int ties;

    @Getter
    @Setter
    private int saves;

    @Getter
    @Setter
    private int shotsAgainst;

    @Getter
    @Setter
    private int goalsAgainst;

    @Getter
    @Setter
    private int shutouts;

    @Getter
    @Setter
    private double savePercentage;

    @Getter
    @Setter
    private double goalsAgainstAverage;

    @Override
    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.seasonString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GoalieSeasonResource that = (GoalieSeasonResource) o;
        return
                this.gamesPlayed == that.gamesPlayed &&
                this.gamesStarted == that.gamesStarted &&
                this.wins == that.wins &&
                this.losses == that.losses &&
                this.ties == that.ties &&
                this.saves == that.saves &&
                this.shotsAgainst == that.shotsAgainst &&
                this.goalsAgainst == that.goalsAgainst &&
                this.shutouts == that.shutouts &&
                Double.compare(that.savePercentage, this.savePercentage) == 0 &&
                Double.compare(that.goalsAgainstAverage, this.goalsAgainstAverage) == 0 &&
                this.seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.seasonString, this.gamesPlayed, this.gamesStarted, this.wins, this.losses, this.ties, this.saves, this.shotsAgainst, this.goalsAgainst, this.shutouts, this.savePercentage, this.goalsAgainstAverage);
    }

    @Override
    public int compareTo(GoalieSeasonResource o) {
        return this.seasonString.compareTo(o.seasonString);
    }
}
