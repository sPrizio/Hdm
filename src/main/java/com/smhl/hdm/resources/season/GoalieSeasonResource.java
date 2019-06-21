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
    private Integer gamesPlayed;

    @Getter
    @Setter
    private Integer gamesStarted;

    @Getter
    @Setter
    private Integer wins;

    @Getter
    @Setter
    private Integer losses;

    @Getter
    @Setter
    private Integer ties;

    @Getter
    @Setter
    private Integer saves;

    @Getter
    @Setter
    private Integer shotsAgainst;

    @Getter
    @Setter
    private Integer goalsAgainst;

    @Getter
    @Setter
    private Integer shutouts;

    @Getter
    @Setter
    private Double savePercentage;

    @Getter
    @Setter
    private Double goalsAgainstAverage;

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
                this.gamesPlayed.equals(that.gamesPlayed) &&
                this.gamesStarted.equals(that.gamesStarted) &&
                this.wins.equals(that.wins) &&
                this.losses.equals(that.losses) &&
                this.ties.equals(that.ties) &&
                this.saves.equals(that.saves) &&
                this.shotsAgainst.equals(that.shotsAgainst) &&
                this.goalsAgainst.equals(that.goalsAgainst) &&
                this.shutouts.equals(that.shutouts) &&
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
