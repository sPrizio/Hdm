package com.smhl.hdm.resources.season;

import com.smhl.hdm.resources.HdmResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * DTO for team seasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class TeamSeasonResource implements HdmResource, Comparable<TeamSeasonResource> {

    @Getter
    @Setter
    private String seasonString;

    @Getter
    @Setter
    private Integer gamesPlayed;

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
    private Integer points;

    @Getter
    @Setter
    private Integer goalsFor;

    @Getter
    @Setter
    private Integer goalsAgainst;

    @Getter
    @Setter
    private Integer differential;

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

        TeamSeasonResource that = (TeamSeasonResource) o;
        return
                this.gamesPlayed.equals(that.gamesPlayed) &&
                this.wins.equals(that.wins) &&
                this.losses.equals(that.losses) &&
                this.ties.equals(that.ties) &&
                this.points.equals(that.points) &&
                this.goalsFor.equals(that.goalsFor) &&
                this.goalsAgainst.equals(that.goalsAgainst) &&
                this.differential.equals(that.differential) &&
                this.seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.seasonString, this.gamesPlayed, this.wins, this.losses, this.ties, this.points, this.goalsFor, this.goalsAgainst, this.differential);
    }

    @Override
    public int compareTo(TeamSeasonResource o) {
        return this.seasonString.compareTo(o.seasonString);
    }
}
