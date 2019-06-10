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
    private int gamesPlayed;

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
    private int points;

    @Getter
    @Setter
    private int goalsFor;

    @Getter
    @Setter
    private int goalsAgainst;

    @Getter
    @Setter
    private int differential;

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
                this.gamesPlayed == that.gamesPlayed &&
                this.wins == that.wins &&
                this.losses == that.losses &&
                this.ties == that.ties &&
                this.points == that.points &&
                this.goalsFor == that.goalsFor &&
                this.goalsAgainst == that.goalsAgainst &&
                this.differential == that.differential &&
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
